package com.dijkstra.performance.scenario;

import java.util.Random;

import com.dijkstra.graph.NeighbourArrayGraphGenerator;
import com.dijkstra.performance.PerformanceScenario;
import com.dijkstra.priority.PriorityObject;
import com.dijkstra.priority.PriorityQueueDijkstra;
import com.dijkstra.priority.impl.TreeSetPriorityQueue;

public class RandomTreeSetPriorityQueueScenario implements PerformanceScenario {

  NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();

  int[] previous;
  PriorityObject[] priorityObjectArray;
  TreeSetPriorityQueue priorityQueue;
  Random random;

  int size;
  double p;
  int previousArrayBuilds;

  public RandomTreeSetPriorityQueueScenario(
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
  public String getScenarioName() {
    return "Tree Set Priority Dijkstra";
  }

  @Override
  public void generateGraph() {
    previous = new int[size];
    generator.generateRandomGraph(size, p, random);
    priorityQueue = new TreeSetPriorityQueue();
    priorityObjectArray = new PriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new PriorityObject(i, 0.0);
    }
  }

  @Override
  public int[] testPrevious(int randomSeed) {
    previous = new int[size];
    Random random = new Random(randomSeed);
    generator.generateRandomGraph(size, p, random);
    priorityQueue = new TreeSetPriorityQueue();
    priorityObjectArray = new PriorityObject[size];
    for (int i = 0; i < size; ++i) {
      priorityObjectArray[i] = new PriorityObject(i, 0.0);
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
