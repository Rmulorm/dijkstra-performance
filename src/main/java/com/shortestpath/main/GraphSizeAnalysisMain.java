package com.shortestpath.main;

import java.io.File;

public class GraphSizeAnalysisMain extends DijkstraPerformanceBase {

  public void run() {
    deletePreviousRunFiles();

    runWithConstantP(0.1);
    //    runWithConstantP(0.3);
    //    runWithConstantP(0.5);
    //    runWithConstantP(0.7);
    //    runWithConstantP(0.9);
  }

  private void runWithConstantP(double p) {
    int n = 20;

    double[][] results = new double[n][];
    for (int i = 0; i < n; ++i) {
      results[i] = parameterizedMeasurement(10 + 10 * i, p);
    }
    for (int i = 0; i < n; ++i) {
      if (results[i] == null) {
        continue;
      }
      for (int j = 0; j < results[i].length; ++j) {
        System.out.print(results[i][j]);
        System.out.print(",");
      }
      System.out.println();
    }
  }

  private void deletePreviousRunFiles() {
    File previousRunFile = new File(ProjectConstants.BRUTE_FORCE_FILE_NAME);
    previousRunFile.delete();

    previousRunFile = new File(ProjectConstants.BASE_DIJKSTRA_FILE_NAME);
    previousRunFile.delete();

    previousRunFile = new File(ProjectConstants.PRIORITY_QUEUE_DIJKSTRA_FILE_NAME);
    previousRunFile.delete();
  }
}
