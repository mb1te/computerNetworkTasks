
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WaitNotifyExample {
	static int cntPrinted = 0;
	static int cntPushed = 0;
	static int x = 0;
	
	public static void main(String[] args) {
		Runnable task = () -> {
			System.out.println("Task started");
			for (int i = 0; i < 20; i++) {
				synchronized(WaitNotifyExample.class) {
					if (cntPrinted == cntPushed) {
						try {
							WaitNotifyExample.class.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					cntPrinted++;
					System.out.printf("Printed %d\n", x);
					WaitNotifyExample.class.notify();
				}
			}
		};
		
		Thread taskThread = new Thread(task);
		taskThread.start();
		
		for (int i = 0; i < 20; i++) {
			synchronized(WaitNotifyExample.class) {
				while (cntPrinted < cntPushed) {
					try {
						WaitNotifyExample.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				x = i * i;
				cntPushed++;
				System.out.printf("Pushed %d\n", i * i);
				WaitNotifyExample.class.notify();
			}
		}
	}
}