package project1;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
		CalcSquares task1 =  new CalcSquares(queue);
		PrintSquares task2 = new PrintSquares(queue);
		
		new Thread(task1).start();
		new Thread(task2).start();
	}
}

class CalcSquares implements Runnable {
	private BlockingQueue<Integer> queue;
	
	public CalcSquares(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 20; i++) queue.put(i * i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PrintSquares implements Runnable {
	private BlockingQueue<Integer> queue;
	
	public PrintSquares(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				int cur;
				cur = queue.take();
				System.out.println(cur);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
