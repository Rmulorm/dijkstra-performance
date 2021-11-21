package com.shortestpath.performance.scenario;

import com.shortestpath.algorithms.ShortestPathGenerator;
import com.shortestpath.main.ProjectConstants;
import java.util.Random;

import com.shortestpath.graph.NeighbourArrayGraphGenerator;
import com.shortestpath.performance.PerformanceScenario;
import com.shortestpath.algorithms.PriorityQueueDijkstra;

public class RandomPriorityQueueDijkstraScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  int[] previous;
  Random random;
  ShortestPathGenerator shortestPathGenerator;

  int size;
  double p;
  int arcs;
  int previousArrayBuilds;

  public RandomPriorityQueueDijkstraScenario(
      int size, double p, int arcs, int previousArrayBuilds, Random random) {
    this.size = size;
    this.p = p;
    this.arcs = arcs;
    this.previousArrayBuilds = previousArrayBuilds;
    this.random = random;
    this.shortestPathGenerator = new PriorityQueueDijkstra();
  }

  @Override
  public void runShortestPath() {
    for (int i = 0; i < previousArrayBuilds; ++i) {
      int origin = random.nextInt(size);
      previous =
          shortestPathGenerator.generateShortestPathForWholeGraph(
              generator.neighbours, generator.weights, origin, size);
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
  public int getArcs() {
    return arcs;
  }

  @Override
  public String getScenarioName() {
    return "Fibonacci Priority Dijkstra";
  }

  @Override
  public String getFileName() {
    return ProjectConstants.PRIORITY_QUEUE_DIJKSTRA_FILE_NAME;
  }

  @Override
  public void generateGraph() {
    previous = new int[size];
    generator.generateRandomGraph(size, p, random);
  }

  @Override
  public int[] testPrevious(int randomSeed) {
    Random random = new Random(randomSeed);
    generator.generateRandomGraph(size, p, random);
    previous = new int[size];
    int origin = random.nextInt(size);
    previous =
        shortestPathGenerator.generateShortestPathForWholeGraph(
            generator.neighbours, generator.weights, origin, size);
    return previous;
  }
}
