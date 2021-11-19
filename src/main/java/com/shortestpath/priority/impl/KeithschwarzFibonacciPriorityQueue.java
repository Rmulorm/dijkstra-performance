package com.shortestpath.priority.impl;

import com.shortestpath.dependencies.keithschwarz.FibonacciHeap;
import com.shortestpath.dependencies.keithschwarz.FibonacciHeap.Entry;

import com.shortestpath.priority.PriorityObject;
import com.shortestpath.priority.PriorityQueue;

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
