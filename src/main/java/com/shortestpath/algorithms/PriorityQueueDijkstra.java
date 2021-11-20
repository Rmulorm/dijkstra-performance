package com.shortestpath.algorithms;

import com.shortestpath.priorityqueue.PriorityObject;
import com.shortestpath.priorityqueue.PriorityQueue;
import com.shortestpath.priorityqueue.impl.KeithschwarzDijkstraPriorityObject;
import com.shortestpath.priorityqueue.impl.KeithschwarzFibonacciPriorityQueue;

public class PriorityQueueDijkstra extends BaseDijkstra {

  private PriorityQueue<PriorityObject> priorityQueue;
  private PriorityObject[] priorityObjectArray;

  @Override
  protected void initializeShortestPath(int size) {
    shortestPath = new int[size];
  }

  @Override
  protected void initializeQueue(int source, int size) {
    priorityQueue = new KeithschwarzFibonacciPriorityQueue();
    priorityObjectArray = new KeithschwarzDijkstraPriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new KeithschwarzDijkstraPriorityObject(i, 0.0);
    }

    for (int i = 0; i < priorityObjectArray.length; ++i) {
      priorityObjectArray[i].priority = Double.MAX_VALUE;
      shortestPath[i] = -1;
    }

    priorityObjectArray[source].priority = 0.0;

    priorityQueue.clear();
    for (PriorityObject priorityObject : priorityObjectArray) {
      priorityQueue.add(priorityObject);
    }
  }

  @Override
  protected int getCurrentQueueSize() {
    return priorityQueue.size();
  }

  @Override
  protected int extractMin() {
    PriorityObject min = priorityQueue.extractMin();
    return min.node;
  }

  @Override
  protected void relax(int neighbour, double distance, int u) {
    double alt = priorityObjectArray[u].priority + distance;
    if (alt < priorityObjectArray[neighbour].priority) {
      priorityQueue.decreasePriority(priorityObjectArray[neighbour], alt);
      shortestPath[neighbour] = u;
    }
  }
}
