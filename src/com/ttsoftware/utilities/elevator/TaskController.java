package com.ttsoftware.utilities.elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TaskController {

	List<Elevator> allElevators = new ArrayList<Elevator>();
	List<Elevator> emptyElevators = new ArrayList<Elevator>();
	List<Elevator> runningElevators = new ArrayList<Elevator>();

	BlockingQueue<Order> taskList = new ArrayBlockingQueue<Order>(1, true);

	private void getAllElevatorInfos() {
		List<Elevator> elevators = new ArrayList<Elevator>();
		// get all of the elevators information from DB system
		allElevators = elevators;
	}

	private void orderProcess(List<Elevator> elevators, Order order) {
		String elevatorId = getBestElevatorForOrder(elevators,
				order.getOrderLayer(), order.isUpstair());
		order.setElevatorId(elevatorId);
		order.setOrderStatus(Order.ORDER_STATUS.READY);
		addOrderTask(order);
	}

	private String getBestElevatorForOrder(List<Elevator> elevators,
			int orderLayer, boolean upstair) {
		Map<String, Long> orderElevatorMap = new HashMap<String, Long>();
		for (int i = 0; i < elevators.size(); i++) {
			Elevator selectedElevator = elevators.get(i);
			int targetLayer = selectedElevator.getTargetLayer();
			int currentLayer = selectedElevator.getCurrentLayer();
			Long overallDistance;
			if (currentLayer == targetLayer) {
				overallDistance = Long.parseLong(String.valueOf(Math
						.abs(targetLayer - orderLayer)));
				orderElevatorMap
						.put(selectedElevator.getElevatorId(), overallDistance);
			} else {
				if ((Elevator.STATUS.UPSTAIR.equals(selectedElevator
						.getStatus()) && upstair && orderLayer > currentLayer && orderLayer < targetLayer)
						|| (Elevator.STATUS.DOWNSTAIR.equals(selectedElevator
								.getStatus())
								&& !upstair
								&& orderLayer < currentLayer && orderLayer > targetLayer)) {
					int distance = Math.abs(orderLayer - currentLayer);
					overallDistance = Long.parseLong(String.valueOf(distance));
				} else {
					int distance = Math.abs(targetLayer - currentLayer);
					int distance2 = Math.abs(orderLayer - targetLayer);
					overallDistance = Long.parseLong(String.valueOf(distance
							+ distance2));
				}
				orderElevatorMap.put(selectedElevator.getElevatorId(),
						overallDistance);
			}
		}

		Map.Entry<String, Long> bestEntry = null;
		Iterator<Map.Entry<String, Long>> iter = orderElevatorMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Long> entry = iter.next();
			if (bestEntry != null) {
				Long value = entry.getValue();
				if (value < bestEntry.getValue()) {
					bestEntry = entry;
				}
			} else {
				bestEntry = entry;
			}
		}
		System.out.println("BestElevatorId:" + bestEntry.getKey()
				+ " , CloseDistance:" + bestEntry.getValue());
		return bestEntry.getKey();
	}

	private void addOrderTask(Order order) {
		(new Thread(new TaskProducer(taskList, order))).start();
		(new Thread(new TaskConsumer(taskList))).start();
	}

	public void acceptElevatorOrderProcess(List<Elevator> elevators,
			List<Order> orderList) {
		if (elevators == null) {
			getAllElevatorInfos();
		} else {
			allElevators.addAll(elevators);
			// allElevators=(List<Elevator>) ((ArrayList) elevators).clone();
			// Collections.copy(allElevators, elevators);
		}
		for (Order currentOrder : orderList) {
			orderProcess(allElevators, currentOrder);
		}
	}

}
