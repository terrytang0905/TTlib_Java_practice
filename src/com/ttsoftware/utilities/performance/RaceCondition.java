package com.ttsoftware.utilities.performance;

import java.util.concurrent.atomic.AtomicLong;

public class RaceCondition implements Runnable{
	private static AtomicLong counter=new AtomicLong();
	private static int threads=10;

	public void run(){
		for(int i=0;i<1000;i++){
			long currentValue=counter.incrementAndGet();
			System.out.println(currentValue);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for(int j=0;j<threads;j++){
			new Thread(new RaceCondition()).start();
		}
	}
}
