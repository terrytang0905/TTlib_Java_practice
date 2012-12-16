package com.ttsoftware.utilities.elevator;

import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {

	private BlockingQueue<Order> taskProducerList;
	private Order order;

	public TaskProducer(
			BlockingQueue<Order> taskProducerList,Order order){
		this.taskProducerList=taskProducerList;
		this.order=order;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			taskProducerList.put(order);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
