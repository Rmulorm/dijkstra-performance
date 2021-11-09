package com.dijkstra.priority.impl;

import com.dijkstra.dependencies.keithschwarz.FibonacciHeap.Entry;

import com.dijkstra.priority.PriorityObject;

public class KeithschwarzDijkstraPriorityObject extends PriorityObject {
	
	public Entry<KeithschwarzDijkstraPriorityObject> entry;

	public KeithschwarzDijkstraPriorityObject(int node, double distance) {
		super(node, distance);
	}

}
