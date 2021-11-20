package com.shortestpath.algorithms;

import java.util.HashSet;

public class ArrayDijkstra extends BaseDijkstra {

  private HashSet<Integer> queue;
  private double[] distances;

  @Override
  protected void initializeShortestPath(int size) {
    shortestPath = new int[size];
  }

  @Override
  protected void initializeQueue(int source, int size) {
    distances = new double[size];

    for (int i = 0; i < size; ++i) {
      distances[i] = Double.MAX_VALUE;
      shortestPath[i] = -1;
    }

    distances[source] = 0.0;

    queue = new HashSet<>();
    for (int i = 0; i < size; ++i) {
      queue.add(i);
    }
  }

  @Override
  protected int getCurrentQueueSize() {
    return queue.size();
  }

  @Override
  protected int extractMin() {
    int u = -1;
    for (int v : queue) {
      u = (u == -1) ? v : (distances[u] > distances[v]) ? v : u;
    }

    queue.remove(u);
    return u;
  }

  @Override
  protected void relax(int neighbour, double distance, int u) {
    double alt = distances[u] + distance;
    if (alt < distances[neighbour]) {
      distances[neighbour] = alt;
      shortestPath[neighbour] = u;
    }
  }
}
