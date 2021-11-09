package com.dijkstra.priority.impl;

import com.dijkstra.dependencies.growingwiththeweb.dataStructures.FibonacciHeap;

import com.dijkstra.priority.PriorityObject;

public class GrowingWithTheWebDijkstraPriorityObject extends PriorityObject {
	
	public FibonacciHeap.Node<PriorityObject> node;

	public GrowingWithTheWebDijkstraPriorityObject(int node, double distance) {
		super(node, distance);
	}

}
