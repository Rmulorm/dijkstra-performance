package com.shortestpath.performance;

public interface PerformanceScenario {
  int getSize();

  double getP();

  int getArcs();

  String getScenarioName();

  String getFileName();

  void generateGraph();

  void runShortestPath();

  int[] testPrevious(int randomSeed);
}
