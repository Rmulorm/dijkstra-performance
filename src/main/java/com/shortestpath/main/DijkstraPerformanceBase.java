package com.shortestpath.main;

import com.shortestpath.performance.scenario.RandomBruteForceScenario;
import java.util.Random;

import com.shortestpath.performance.PerformanceEngine;
import com.shortestpath.performance.PerformanceScenario;
import com.shortestpath.performance.scenario.RandomBaseDijkstraScenario;
import com.shortestpath.performance.scenario.RandomPriorityQueueDijkstraScenario;

public class DijkstraPerformanceBase {

  private static final int RANDOM_SEED = 42;
  private static final int PREVIOUS_ARRAY_BUILD = 20;

  private static final int REPEATS = 20;
  private static final boolean SHOULD_PRINT_AVERAGE_TIMES = true;
  private static final boolean SHOULD_PRINT_OUT_INNER_RESULTS = false;
  private static final int SKIP_LOW = 3;
  private static final int SKIP_HIGH = 3;

  protected int calculateArcNumber(int size, double p) {
    return Math.max((int) Math.round(size * size * p) - (size - 1) * 2, (size - 1) * 2);
  }

  protected double[] parameterizedMeasurement(int size, double p) {
    System.out.println("Size: " + size + ", p: " + p + ", #arcs: " + calculateArcNumber(size, p));

    int arcs = calculateArcNumber(size, p);

    PerformanceScenario scenario;
    scenario =
        new RandomBruteForceScenario(size, p, arcs, PREVIOUS_ARRAY_BUILD, new Random(RANDOM_SEED));
    int[] p0 = testPreviousForScenario(scenario);
    double m0 = measureScenario(scenario);

    scenario =
        new RandomBaseDijkstraScenario(
            size, p, arcs, PREVIOUS_ARRAY_BUILD, new Random(RANDOM_SEED));
    int[] p1 = testPreviousForScenario(scenario);
    double m1 = measureScenario(scenario);

    scenario =
        new RandomPriorityQueueDijkstraScenario(
            size, p, arcs, PREVIOUS_ARRAY_BUILD, new Random(RANDOM_SEED));
    int[] p2 = testPreviousForScenario(scenario);
    double m2 = measureScenario(scenario);

    // check previous arrays from the test runs
    for (int i = 0; i < p1.length; ++i) {
      if (p0[i] != p1[i] || p1[i] != p2[i]) {
        throw new RuntimeException("Problem...");
      }
    }

    return new double[] {size, p, arcs, m0, m1, m2};
  }

  private int[] testPreviousForScenario(PerformanceScenario scenario) {
    return scenario.testPrevious(RANDOM_SEED);
  }

  private double measureScenario(PerformanceScenario scenario) {
    PerformanceEngine engine = new PerformanceEngine(scenario);
    return engine.measurement(
        REPEATS, SHOULD_PRINT_AVERAGE_TIMES, SHOULD_PRINT_OUT_INNER_RESULTS, SKIP_LOW, SKIP_HIGH);
  }
}
