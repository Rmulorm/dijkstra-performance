package com.shortestpath.algorithms;

import java.util.HashSet;

public class BruteForce {
  public static void createPreviousArray(
      int[][] neighbours, double[][] weights, int source, double[] distance, int[] previous) {

    int largestNodeId = neighbours.length;

    for (int i = 0; i < largestNodeId; ++i) {
      distance[i] = Double.MAX_VALUE;
      previous[i] = -1;
    }

    distance[source] = 0.0;

    HashSet<Integer> vertices = new HashSet<>();
    for (int i = 0; i < largestNodeId; ++i) {
      vertices.add(i);
    }

    vertices.remove(source);
    createPreviousArray(neighbours, weights, distance, previous, vertices, source);
  }

  private static void createPreviousArray(
      int[][] neighbours,
      double[][] weights,
      double[] distance,
      int[] previous,
      HashSet<Integer> vertices,
      int currentPosition) {
    if (vertices.isEmpty()) {
      return;
    }

    for (int i = 0; i < neighbours[currentPosition].length; i++) {
      int neighbour = neighbours[currentPosition][i];
      if (distance[neighbour] > distance[currentPosition] + weights[currentPosition][i]
          && vertices.contains(neighbours[currentPosition][i])) {
        previous[neighbour] = currentPosition;
        distance[neighbour] = distance[currentPosition] + weights[currentPosition][i];
        vertices.remove(currentPosition);
        createPreviousArray(neighbours, weights, distance, previous, vertices, neighbour);
        vertices.add(currentPosition);
      }
    }
  }
}
