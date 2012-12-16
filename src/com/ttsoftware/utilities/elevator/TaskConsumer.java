package com.ttsoftware.utilities.elevator;

import java.util.concurrent.BlockingQueue;

public class TaskConsumer implements Runnable {

	private BlockingQueue<Order> taskConsumerList;

	public TaskConsumer(
			BlockingQueue<Order> taskConsumerList){
		this.taskConsumerList=taskConsumerList;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Order order=null;
			while((order=taskConsumerList.take())!=null){
				callElevatorTaskByOrder(order);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void callElevatorTaskByOrder(Order order){
		
	}
}
