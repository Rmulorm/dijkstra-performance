package com.shortestpath.algorithms;

import java.util.HashSet;

public class BaseDijkstra implements ShortestPathGenerator {

  @Override
  public int[] generateShortestPathForWholeGraph(
      int[][] neighbours, double[][] weights, int source, int size) {
    int[] shortestPath = new int[size];
    double[] distances = new double[size];

    int largestNodeId = neighbours.length;

    for (int i = 0; i < largestNodeId; ++i) {
      distances[i] = Double.MAX_VALUE;
      shortestPath[i] = -1;
    }

    distances[source] = 0.0;

    HashSet<Integer> vertices = new HashSet<>();
    for (int i = 0; i < largestNodeId; ++i) {
      vertices.add(i);
    }

    while (vertices.size() != 0) {

      int u = -1;

      // search the element where the distance is minimum
      for (int v : vertices) {
        if (u == -1) {
          u = v;
        } else {
          if (distances[u] > distances[v]) {
            u = v;
          }
        }
      }

      vertices.remove(u);
      // find the neighbours
      if (neighbours[u] == null) {
        continue;
      }

      for (int i = 0; i < neighbours[u].length; ++i) {
        double alt = distances[u] + weights[u][i];
        if (alt < distances[neighbours[u][i]]) {
          distances[neighbours[u][i]] = alt;
          shortestPath[neighbours[u][i]] = u;
        }
      }
    }

    return shortestPath;
  }
}
