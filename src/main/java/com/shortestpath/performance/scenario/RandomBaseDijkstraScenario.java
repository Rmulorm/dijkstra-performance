package com.shortestpath.performance.scenario;

import com.shortestpath.main.ProjectConstants;
import java.util.Random;

import com.shortestpath.algorithms.BaseDijkstra;
import com.shortestpath.graph.NeighbourArrayGraphGenerator;
import com.shortestpath.performance.PerformanceScenario;

public class RandomBaseDijkstraScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  double[] distance;
  int[] previous;
  Random random;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomBaseDijkstraScenario(int size, double p, int previousArrayBuilds, Random random) {
    this.size = size;
    this.p = p;
    this.previousArrayBuilds = previousArrayBuilds;
    this.random = random;
  }

  @Override
  public void runShortestPath() {
    for (int i = 0; i < previousArrayBuilds; ++i) {
      int origin = random.nextInt(size);
      BaseDijkstra.createPreviousArray(
          generator.neighbours, generator.weights, origin, distance, previous);
    }
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public double getP() {
    return p;
  }

  @Override
  public String getScenarioName() {
    return "Base Dijkstra";
  }

  @Override
  public String getFileName() {
    return ProjectConstants.BASE_DIJKSTRA_FILE_NAME;
  }

  @Override
  public void generateGraph() {
    distance = new double[size];
    previous = new int[size];
    generator.generateRandomGraph(size, p, random);
  }

  @Override
  public int[] testPrevious(int randomSeed) {
    Random random = new Random(randomSeed);
    generator.generateRandomGraph(size, p, random);
    int origin = random.nextInt(size);
    distance = new double[size];
    previous = new int[size];
    //		System.out.println("origin: " + origin);
    BaseDijkstra.createPreviousArray(
        generator.neighbours, generator.weights, origin, distance, previous);
    return previous;
  }
}
