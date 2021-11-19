package com.shortestpath.algorithms;

public interface MinimumSpanningTreeGenerator {
  int[] generateMinimumSpanningTree(
      int[][] neighbours, double[][] weights, int source, int size);
}
