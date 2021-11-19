package com.shortestpath.priority.impl;

import com.shortestpath.dependencies.keithschwarz.FibonacciHeap.Entry;

import com.shortestpath.priority.PriorityObject;

public class KeithschwarzDijkstraPriorityObject extends PriorityObject {
	
	public Entry<KeithschwarzDijkstraPriorityObject> entry;

	public KeithschwarzDijkstraPriorityObject(int node, double distance) {
		super(node, distance);
	}

}
