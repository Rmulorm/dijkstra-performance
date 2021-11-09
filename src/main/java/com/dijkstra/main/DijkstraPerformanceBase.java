package com.dijkstra.main;

import java.util.Random;

import com.dijkstra.performance.PerformanceEngine;
import com.dijkstra.performance.PerformanceScenario;
import com.dijkstra.performance.scenario.RandomBaseScenario;
import com.dijkstra.performance.scenario.RandomGrowingWithTheWebFibonacciPriorityQueueScenario;
import com.dijkstra.performance.scenario.RandomKeithschwarzFibonacciPriorityQueueScenario;
import com.dijkstra.performance.scenario.RandomTreeSetPriorityQueueScenario;

public class DijkstraPerformanceBase {
		
	protected int calculateArcNumber(int size, double p) {
		return Math.max((int)Math.round(size * size * p) - (size - 1) * 2, (size - 1) * 2);
	}
		
	protected double[] parameterizedMeasurement(int size, double p) {
		
		System.out.println("Size: " + size + ", p: " + p + ", #arcs: " + calculateArcNumber(size, p));
		
		PerformanceScenario scenarioBase = new RandomBaseScenario(size, p, 20, new Random(42));
		PerformanceScenario scenarioPriorityQueue = new RandomTreeSetPriorityQueueScenario(size, p, 20, new Random(42));
		PerformanceScenario scenarioGrowingWithTheWebFibonacciPriorityQueue = new RandomGrowingWithTheWebFibonacciPriorityQueueScenario(size, p, 20, new Random(42));
		PerformanceScenario scenarioKeithschwarzFibonacciPriorityQueue = new RandomKeithschwarzFibonacciPriorityQueueScenario(size, p, 20, new Random(42));
	
		int[] p0 = scenarioBase.testPrevious(42);
		PerformanceEngine engine0 = new PerformanceEngine(scenarioBase);
		double m0 = engine0.measurement(20, true, false, 3, 3);
		
		int[] p1 = scenarioPriorityQueue.testPrevious(42);
		PerformanceEngine engine1 = new PerformanceEngine(scenarioPriorityQueue);
		double m1 = engine1.measurement(20, true, false, 3, 3);
		
		int[] p5 = scenarioGrowingWithTheWebFibonacciPriorityQueue.testPrevious(42);
		PerformanceEngine engine5 = new PerformanceEngine(scenarioGrowingWithTheWebFibonacciPriorityQueue);
		double m5 = engine5.measurement(20, true, false, 3, 3);
		
		int[] p7 = scenarioKeithschwarzFibonacciPriorityQueue.testPrevious(42);
		PerformanceEngine engine7 = new PerformanceEngine(scenarioKeithschwarzFibonacciPriorityQueue);
		double m7 = engine7.measurement(20, true, false, 3, 3);
		
		// check previous arrays from the test runs
		for (int i = 0; i < p0.length; ++i) {
			if (p0[i] != p1[i] || p0[i] != p5[i] || p0[i] != p7[i]) {
				throw new RuntimeException("Problem...");
			}
		}
		
		return new double[] { size, p, calculateArcNumber(size, p), m0, m1, m5, m7 };
	}
}
