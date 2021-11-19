package com.shortestpath.priorityqueue.impl;

import com.shortestpath.priorityqueue.dependencies.keithschwarz.FibonacciHeap.Entry;

import com.shortestpath.priorityqueue.PriorityObject;

public class KeithschwarzDijkstraPriorityObject extends PriorityObject {
	
	public Entry<KeithschwarzDijkstraPriorityObject> entry;

	public KeithschwarzDijkstraPriorityObject(int node, double distance) {
		super(node, distance);
	}

}
