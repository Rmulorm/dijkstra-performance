package com.dijkstra.priority.impl;

import com.dijkstra.dependencies.growingwiththeweb.dataStructures.FibonacciHeap;
import com.dijkstra.dependencies.growingwiththeweb.dataStructures.FibonacciHeap.Node;

import com.dijkstra.priority.PriorityObject;
import com.dijkstra.priority.PriorityQueue;

public class GrowingWithTheWebFibonacciPriorityQueue implements PriorityQueue<PriorityObject> {
	
	FibonacciHeap<PriorityObject> heap = new FibonacciHeap<>();
	int heapSize = 0;
	
	@Override
	public void add(PriorityObject item) {
		Node<PriorityObject> node = heap.insert(item);
		((GrowingWithTheWebDijkstraPriorityObject)item).node = node;
		++heapSize;
	}

	@Override
	public void decreasePriority(PriorityObject item, double priority) {
		item.priority = priority;
		heap.decreaseKey(((GrowingWithTheWebDijkstraPriorityObject)item).node, item);
	}

	@Override
	public PriorityObject extractMin() {
		if (heapSize > 0) {
			--heapSize;
			return heap.extractMin().getKey();
		} else {
			return null;
		}
	}

	@Override
	public void clear() {
		heap.clear();
		heapSize = 0;
	}

	@Override
	public int size() {
		return heapSize;
	}	

}
