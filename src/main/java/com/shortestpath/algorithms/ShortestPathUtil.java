package com.shortestpath.algorithms;

import java.util.LinkedList;

public final class ShortestPathUtil {

  private ShortestPathUtil() {}

  public static int[] shortestPath(int[] previous, int destination) {
    if (previous[destination] == -1) {
      return null;
    }

    LinkedList<Integer> reversedRoute = new LinkedList<>();
    int u = destination;

    while (u != -1) {
      reversedRoute.add(u);
      u = previous[u];
    }

    int[] path = new int[reversedRoute.size()];
    for (int i = 0; i < path.length; ++i) {
      path[i] = reversedRoute.get(path.length - 1 - i);
    }

    return path;
  }
}
