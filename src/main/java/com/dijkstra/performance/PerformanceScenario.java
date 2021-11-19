package com.dijkstra.performance;

public interface PerformanceScenario {
  int getSize();

  double getP();

  String getScenarioName();

  String getFileName();

  void generateGraph();

  void runShortestPath();

  int[] testPrevious(int randomSeed);
}
