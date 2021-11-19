package com.shortestpath.algorithms;

import com.shortestpath.priorityqueue.PriorityObject;
import com.shortestpath.priorityqueue.PriorityQueue;
import com.shortestpath.priorityqueue.impl.KeithschwarzDijkstraPriorityObject;
import com.shortestpath.priorityqueue.impl.KeithschwarzFibonacciPriorityQueue;

public class PriorityQueueDijkstra implements MinimumSpanningTreeGenerator {

  @Override
  public int[] generateMinimumSpanningTree(
      int[][] neighbours, double[][] weights, int source, int size) {
    int[] minimumSpanningTree = new int[size];

    PriorityQueue<PriorityObject> priorityQueue = new KeithschwarzFibonacciPriorityQueue();
    PriorityObject[] priorityObjectArray = new KeithschwarzDijkstraPriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new KeithschwarzDijkstraPriorityObject(i, 0.0);
    }

    for (int i = 0; i < priorityObjectArray.length; ++i) {
      priorityObjectArray[i].priority = Double.MAX_VALUE;
      minimumSpanningTree[i] = -1;
    }

    priorityObjectArray[source].priority = 0.0;

    priorityQueue.clear();
    for (PriorityObject priorityObject : priorityObjectArray) {
      priorityQueue.add(priorityObject);
    }

    while (priorityQueue.size() != 0) {

      // extract min
      PriorityObject min = priorityQueue.extractMin();
      int u = min.node;

      // find the neighbours
      if (neighbours[u] == null) {
        continue;
      }

      for (int i = 0; i < neighbours[u].length; ++i) {
        double alt = priorityObjectArray[u].priority + weights[u][i];
        if (alt < priorityObjectArray[neighbours[u][i]].priority) {
          priorityQueue.decreasePriority(priorityObjectArray[neighbours[u][i]], alt);
          minimumSpanningTree[neighbours[u][i]] = u;
        }
      }
    }

    return minimumSpanningTree;
  }
}
