package com.dijkstra.performance.scenario;

import com.dijkstra.main.ProjectConstants;
import java.util.Random;

import com.dijkstra.graph.NeighbourArrayGraphGenerator;
import com.dijkstra.performance.PerformanceScenario;
import com.dijkstra.priority.PriorityQueueDijkstra;
import com.dijkstra.priority.impl.KeithschwarzDijkstraPriorityObject;
import com.dijkstra.priority.impl.KeithschwarzFibonacciPriorityQueue;

public class RandomFibonacciPriorityQueueDijkstraScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  int[] previous;
  KeithschwarzDijkstraPriorityObject[] priorityObjectArray;
  KeithschwarzFibonacciPriorityQueue priorityQueue;
  Random random;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomFibonacciPriorityQueueDijkstraScenario(
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
