package com.dijkstra.priority.impl;

import com.dijkstra.priority.PriorityQueue;
import java.util.TreeSet;

import com.dijkstra.priority.PriorityObject;

public class TreeSetPriorityQueue implements PriorityQueue<PriorityObject> {
	
	TreeSet<PriorityObject> tree = new TreeSet<PriorityObject>();

	@Override
	public void add(PriorityObject item) {
		tree.add(item);
	}

	@Override
	public void decreasePriority(PriorityObject item, double priority) {
		tree.remove(item);
		item.priority = priority;
		tree.add(item);
	}

	@Override
	public PriorityObject extractMin() {
		return tree.pollFirst();
	}

	@Override
	public void clear() {
		tree.clear();
	}

	@Override
	public int size() {
		return tree.size();
	}	

}
