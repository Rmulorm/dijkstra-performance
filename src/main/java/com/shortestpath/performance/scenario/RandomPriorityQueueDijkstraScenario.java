package com.shortestpath.performance.scenario;

import com.shortestpath.main.ProjectConstants;
import java.util.Random;

import com.shortestpath.graph.NeighbourArrayGraphGenerator;
import com.shortestpath.performance.PerformanceScenario;
import com.shortestpath.algorithms.PriorityQueueDijkstra;
import com.shortestpath.priorityqueue.impl.KeithschwarzDijkstraPriorityObject;
import com.shortestpath.priorityqueue.impl.KeithschwarzFibonacciPriorityQueue;

public class RandomPriorityQueueDijkstraScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  int[] previous;
  KeithschwarzDijkstraPriorityObject[] priorityObjectArray;
  KeithschwarzFibonacciPriorityQueue priorityQueue;
  Random random;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomPriorityQueueDijkstraScenario(
      int size, double p, int previousArrayBuilds, Random random) {
    this.size = size;
    this.p = p;
    this.previousArrayBuilds = previousArrayBuilds;
    this.random = random;
  }

  @Override
  public void runShortestPath() {
    for (int i = 0; i < previousArrayBuilds; ++i) {
      int origin = random.nextInt(size);
      PriorityQueueDijkstra.createPreviousArray(
          generator.neighbours,
          generator.weights,
          origin,
          previous,
          priorityObjectArray,
          priorityQueue);
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
    priorityQueue = new KeithschwarzFibonacciPriorityQueue();
    priorityObjectArray = new KeithschwarzDijkstraPriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new KeithschwarzDijkstraPriorityObject(i, 0.0);
    }
  }

  @Override
  public int[] testPrevious(int randomSeed) {
    Random random = new Random(randomSeed);
    previous = new int[size];
    generator.generateRandomGraph(size, p, random);
    priorityQueue = new KeithschwarzFibonacciPriorityQueue();
    priorityObjectArray = new KeithschwarzDijkstraPriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new KeithschwarzDijkstraPriorityObject(i, 0.0);
    }
    int origin = random.nextInt(size);
    //		System.out.println("origin: " + origin);
    PriorityQueueDijkstra.createPreviousArray(
        generator.neighbours,
        generator.weights,
        origin,
        previous,
        priorityObjectArray,
        priorityQueue);
    return previous;
  }
}
