package com.dijkstra.performance.scenario;

import com.dijkstra.base.BaseDijkstra;
import com.dijkstra.graph.NeighbourArrayGraphGenerator;
import com.dijkstra.performance.PerformanceScenario;
import java.util.Random;

public class RandomBruteForceScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  double[] distance;
  int[] previous;
  Random random;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomBruteForceScenario(int size, double p, int previousArrayBuilds, Random random) {
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
  public String getScenarioName() {
    return "Base Dijkstra";
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
