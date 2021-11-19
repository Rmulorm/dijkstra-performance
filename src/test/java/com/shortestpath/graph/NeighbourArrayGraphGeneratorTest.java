package com.shortestpath.graph;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;


import com.shortestpath.base.BaseDijkstra;
import org.junit.jupiter.api.Test;

public class NeighbourArrayGraphGeneratorTest {
	
	@Test
	public void test() {
		NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();
		generator.generateRandomGraph(10, 0, new Random(42));
		
		int arcs = 0;
		for (int i = 0; i < generator.neighbours.length; ++i) {
			if (generator.neighbours[i] == null) {
				continue;
			}
			arcs += generator.neighbours[i].length;
		}

		assertNotNull(generator.neighbours);
		assertEquals(18, arcs);
	}
	
	@Test
	public void testDijkstra() {
		NeighbourArrayGraphGenerator generator = new NeighbourArrayGraphGenerator();
		generator.generateRandomGraph(10, 0, new Random(42));
		
		int[] previous = new int[10];
		double[] distance = new double[10];
		
		for (int origin = 0; origin < 10; ++origin) {
			BaseDijkstra.createPreviousArray(generator.neighbours, generator.weights, origin, distance, previous);
			boolean valid = true;
			for (int i = 0; i < 10; ++i) {
				if (i == origin) {
					if (previous[i] != -1) {
						valid = false;
						break;
					}
					continue;
				}
				if (previous[i] == -1) {
					valid = false;
					break;
				}
			}
			assertTrue(valid);
		}
		
	}
	
}
