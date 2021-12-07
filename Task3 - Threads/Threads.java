package project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Threads {
	public static void main(String[] args) throws InterruptedException {
		run(3);
	}
	
	public static void run(int numberOfThreads) throws InterruptedException {
		long startTime = System.currentTimeMillis();;
		int start = 100000000, finish = 400000001;
		int step = (finish - start + numberOfThreads - 1) / numberOfThreads;
		
		List<Task> counts = new ArrayList<Task>();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < numberOfThreads; i++) {
			int curStart = start + i * step, curFinish = Math.min(finish, start + (i + 1) * step);
			Task curTask = new Task(curStart, curFinish);
			Thread curThread = new Thread(curTask);
			counts.add(curTask);
			threads.add(curThread);
			curThread.start();
		}
		
		for (Thread i : threads) i.join();
		
		int answer = 0;
		for (Task i : counts) answer += i.getCnt();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Threads: " + Integer.toString(numberOfThreads));
		System.out.println("Answer: " + Integer.toString(answer));
		System.out.println("Time: " + Long.toString(endTime - startTime));
		System.out.println();
	}
	
}


class Task implements Runnable {
	private int start, finish;
	private int cnt = 0;
	private boolean done = false;
	
	public Task(int start, int finish) {
		this.start= start;
		this.finish = finish;
	}
	
	public void run () {
		System.err.println("started " + Thread.currentThread().getName());
		int divisor = 11 * 13 * 17;
		for (int i = start; i < finish; i++) {
			if (i % divisor == 0) cnt++;
		}
		System.err.println("stopped " + Thread.currentThread().getName());
	}
	
	public int getCnt() throws InterruptedException {
		return cnt;
	}
}

