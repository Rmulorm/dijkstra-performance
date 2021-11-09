package com.dijkstra.priority.impl;

import com.dijkstra.dependencies.keithschwarz.FibonacciHeap;
import com.dijkstra.dependencies.keithschwarz.FibonacciHeap.Entry;

import com.dijkstra.priority.PriorityObject;
import com.dijkstra.priority.PriorityQueue;

public class KeithschwarzFibonacciPriorityQueue implements PriorityQueue<PriorityObject> {
	
	FibonacciHeap<KeithschwarzDijkstraPriorityObject> heap = new FibonacciHeap<>();

	@Override
	public void add(PriorityObject item) {
		Entry<KeithschwarzDijkstraPriorityObject> entry = heap.enqueue((KeithschwarzDijkstraPriorityObject)item, item.priority);
		((KeithschwarzDijkstraPriorityObject)item).entry = entry;
	}

	@Override
	public void decreasePriority(PriorityObject item, double priority) {
		item.priority = priority;
		heap.decreaseKey(((KeithschwarzDijkstraPriorityObject)item).entry, priority);
	}

	@Override
	public PriorityObject extractMin() {
		return heap.dequeueMin().getValue();
	}

	@Override
	public void clear() {
		heap = new FibonacciHeap<>();
	}

	@Override
	public int size() {
		return heap.size();
	}	

}
