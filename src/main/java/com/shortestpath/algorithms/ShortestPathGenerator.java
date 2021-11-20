package com.shortestpath.algorithms;

public interface ShortestPathGenerator {
  int[] generateShortestPathForWholeGraph(
      int[][] neighbours, double[][] weights, int source, int size);
}
