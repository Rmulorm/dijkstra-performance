package com.dijkstra.priority.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.dijkstra.priority.PriorityObject;
import org.junit.jupiter.api.Test;

public class GrowingWithTheWebFibonacciPrioityQueueTest {
	@Test
	public void extractTest() {
		
		GrowingWithTheWebDijkstraPriorityObject[] array = new GrowingWithTheWebDijkstraPriorityObject[] {
			new GrowingWithTheWebDijkstraPriorityObject(2, 12.0),
			new GrowingWithTheWebDijkstraPriorityObject(1, 23.0),
			new GrowingWithTheWebDijkstraPriorityObject(0, 18.0)
		};		
		
		GrowingWithTheWebFibonacciPriorityQueue priorityQueue = new GrowingWithTheWebFibonacciPriorityQueue();
		priorityQueue.add(array[0]);
		priorityQueue.add(array[1]);
		priorityQueue.add(array[2]);
		
		PriorityObject min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(2, min.node);
		assertEquals(2, priorityQueue.size());
		
		min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(0, min.node);
		assertEquals(1, priorityQueue.size());
		
		min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(1, min.node);
		assertEquals(0, priorityQueue.size());

		min = priorityQueue.extractMin();
		assertNotNull(min);
	}
	
	@Test
	public void decreaseTest() {
		
		GrowingWithTheWebDijkstraPriorityObject[] array = new GrowingWithTheWebDijkstraPriorityObject[] {
			new GrowingWithTheWebDijkstraPriorityObject(2, 12.0),
			new GrowingWithTheWebDijkstraPriorityObject(1, 23.0),
			new GrowingWithTheWebDijkstraPriorityObject(0, 18.0)
		};		
		
		GrowingWithTheWebFibonacciPriorityQueue priorityQueue = new GrowingWithTheWebFibonacciPriorityQueue();
		priorityQueue.add(array[0]);
		priorityQueue.add(array[1]);
		priorityQueue.add(array[2]);
		
		priorityQueue.decreasePriority(array[1], 5.0);
		assertEquals(3, priorityQueue.size());
		
		PriorityObject min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(1, min.node);
		assertEquals(2, priorityQueue.size());
		
		min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(2, min.node);
		assertEquals(1, priorityQueue.size());
		
		min = priorityQueue.extractMin();
		assertNotNull(min);
		assertEquals(0, min.node);
		assertEquals(0, priorityQueue.size());

		min = priorityQueue.extractMin();
		assertNotNull(min);
	}	

}
