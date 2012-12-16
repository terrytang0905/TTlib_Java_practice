package com.ttsoftware.utilities.elevator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ElevatorTest {
	
	private List<Elevator> inputElevators(){
		List<Elevator> allElevators = new ArrayList<Elevator>();
		Elevator elevator=new Elevator();
		elevator.setElevatorId("1");
		elevator.setCurrentLayer(3);
		elevator.setTargetLayer(7);
		elevator.setStatus(Elevator.STATUS.UPSTAIR);
		allElevators.add(elevator);
		
		Elevator elevator2=new Elevator();
		elevator2.setElevatorId("2");
		elevator2.setCurrentLayer(4);
		elevator2.setTargetLayer(2);
		elevator2.setStatus(Elevator.STATUS.DOWNSTAIR);
		allElevators.add(elevator2);
		
		Elevator elevator3=new Elevator();
		elevator3.setElevatorId("3");
		elevator3.setCurrentLayer(15);
		elevator3.setTargetLayer(9);
		elevator3.setStatus(Elevator.STATUS.DOWNSTAIR);
		allElevators.add(elevator3);
		return allElevators;
	}
	
	private List<Order> inputOrder(){
		List<Order> orders = new ArrayList<Order>();
		Order order=new Order();
		order.setOrderId("1");
		order.setOrderLayer(5);
		order.setUpstair(true);
		order.setOrderStatus(Order.ORDER_STATUS.SUBMIT);
		orders.add(order);
		return orders;
	}
	
	@Test
	public void testElevatorOrder(){
		TaskController tc=new TaskController();
		tc.acceptElevatorOrderProcess(inputElevators(),inputOrder());
//		assertEquals("reverse String:","lufsseccu",result);
	}
	
}
