package com.dijkstra.performance;

public interface PerformanceScenario {
  public String getScenarioName();

  public void generateGraph();

  public void runShortestPath();

  public int[] testPrevious(int randomSeed);
}
