package com.shortestpath.algorithms;

import com.shortestpath.priorityqueue.PriorityObject;
import com.shortestpath.priorityqueue.PriorityQueue;

public class PriorityQueueDijkstra {

  public static void createPreviousArray(
      int[][] neighbours,
      double[][] weights,
      int source,
      int[] previous,
      PriorityObject[] priorityObjectArray,
      PriorityQueue<PriorityObject> priorityQueue) {

    for (int i = 0; i < priorityObjectArray.length; ++i) {
      priorityObjectArray[i].priority = Double.MAX_VALUE;
      previous[i] = -1;
    }

    priorityObjectArray[source].priority = 0.0;

    priorityQueue.clear();
    for (int i = 0; i < priorityObjectArray.length; ++i) {
      priorityQueue.add(priorityObjectArray[i]);
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
          previous[neighbours[u][i]] = u;
        }
      }
    }
  }
}
