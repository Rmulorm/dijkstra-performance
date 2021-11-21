package com.shortestpath.main;

import com.shortestpath.performance.CsvLine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class GraphSizeAnalysisMain extends DijkstraPerformanceBase {

  public void run() throws IOException {
    prepareOutputFiles();

    runWithConstantP(0.2);
    //    runWithConstantP(0.3);
    //    runWithConstantP(0.5);
    //    runWithConstantP(0.7);
    //    runWithConstantP(0.9);
  }

  private void runWithConstantP(double p) {
    int n = 50;

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

  private void prepareOutputFiles() throws IOException {
    deletePreviousRunFiles();

    createNewFileWithHeaders(ProjectConstants.BRUTE_FORCE_FILE_NAME);
    createNewFileWithHeaders(ProjectConstants.BASE_DIJKSTRA_FILE_NAME);
    createNewFileWithHeaders(ProjectConstants.PRIORITY_QUEUE_DIJKSTRA_FILE_NAME);
  }

  private void createNewFileWithHeaders(String filename) throws IOException {
    FileWriter out = new FileWriter(filename);
    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withDelimiter(';'))) {
      printer.printRecord(CsvLine.csvHeaders());
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
