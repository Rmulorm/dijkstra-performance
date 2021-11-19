package com.shortestpath.performance.scenario;

import com.shortestpath.algorithms.BruteForce;
import com.shortestpath.algorithms.MinimumSpanningTreeGenerator;
import com.shortestpath.graph.NeighbourArrayGraphGenerator;
import com.shortestpath.main.ProjectConstants;
import com.shortestpath.performance.PerformanceScenario;
import java.util.Random;

public class RandomBruteForceScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  int[] previous;
  Random random;
  MinimumSpanningTreeGenerator minimumspanningTreeGenerator;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomBruteForceScenario(int size, double p, int previousArrayBuilds, Random random) {
    this.size = size;
    this.p = p;
    this.previousArrayBuilds = previousArrayBuilds;
    this.random = random;
    this.minimumspanningTreeGenerator = new BruteForce();
  }

  @Override
  public void runShortestPath() {
    for (int i = 0; i < previousArrayBuilds; ++i) {
      int origin = random.nextInt(size);
      previous = minimumspanningTreeGenerator.generateMinimumSpanningTree(
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
  public String getScenarioName() {
    return "Base Brute Force";
  }

  @Override
  public String getFileName() {
    return ProjectConstants.BRUTE_FORCE_FILE_NAME;
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
    int origin = random.nextInt(size);
    previous =  minimumspanningTreeGenerator.generateMinimumSpanningTree(
        generator.neighbours, generator.weights, origin, size);
    return previous;
  }
}
