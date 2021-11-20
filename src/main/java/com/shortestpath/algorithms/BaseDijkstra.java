package com.shortestpath.algorithms;

public abstract class BaseDijkstra implements ShortestPathGenerator {

  protected int[] shortestPath;

  @Override
  public int[] generateShortestPathForWholeGraph(
      int[][] neighbours, double[][] weights, int source, int size) {
    initializeShortestPath(size);

    initializeQueue(source, size);

    while (getCurrentQueueSize() != 0) {
      int u = extractMin();

      if (neighbours[u] == null) {
        continue;
      }

      for (int i = 0; i < neighbours[u].length; ++i) {
        relax(neighbours[u][i], weights[u][i], u);
      }
    }

    return shortestPath;
  }

  protected abstract void initializeShortestPath(int size);

  protected abstract void initializeQueue(int source, int size);

  protected abstract int getCurrentQueueSize();

  protected abstract int extractMin();

  protected abstract void relax(int i, double v, int u);
}
