package com.shortestpath.algorithms;

import java.util.HashSet;

public class BruteForce implements MinimumSpanningTreeGenerator {

  @Override
  public int[] generateMinimumSpanningTree(
      int[][] neighbours, double[][] weights, int source, int size) {
    int[] minimumSpanningTree = new int[size];
    double[] distances = new double[size];

    int largestNodeId = neighbours.length;

    for (int i = 0; i < largestNodeId; ++i) {
      distances[i] = Double.MAX_VALUE;
      minimumSpanningTree[i] = -1;
    }

    distances[source] = 0.0;

    HashSet<Integer> vertices = new HashSet<>();
    for (int i = 0; i < largestNodeId; ++i) {
      vertices.add(i);
    }

    vertices.remove(source);

    return generateMinimumSpanningTree(neighbours, weights, distances, minimumSpanningTree, vertices, source);
  }

  private static int[] generateMinimumSpanningTree(
      int[][] neighbours,
      double[][] weights,
      double[] distances,
      int[] minimumSpanningTree,
      HashSet<Integer> vertices,
      int currentPosition) {
    if (vertices.isEmpty()) {
      return minimumSpanningTree;
    }

    for (int i = 0; i < neighbours[currentPosition].length; i++) {
      int neighbour = neighbours[currentPosition][i];
      if (distances[neighbour] > distances[currentPosition] + weights[currentPosition][i]
          && vertices.contains(neighbours[currentPosition][i])) {
        minimumSpanningTree[neighbour] = currentPosition;
        distances[neighbour] = distances[currentPosition] + weights[currentPosition][i];
        vertices.remove(currentPosition);
        generateMinimumSpanningTree(neighbours, weights, distances, minimumSpanningTree, vertices, neighbour);
        vertices.add(currentPosition);
      }
    }
    return minimumSpanningTree;
  }
}
