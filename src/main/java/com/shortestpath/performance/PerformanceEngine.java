package com.shortestpath.performance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class PerformanceEngine {

  private final PerformanceScenario scenario;

  public long[] startTimes;
  public long[] graphGenerationTimes;
  public long[] endTimes;

  public PerformanceEngine(PerformanceScenario scenario) {
    this.scenario = scenario;
  }

  public double measurement(
      int repeats,
      boolean printAverageTimes,
      boolean printOutInnerResults,
      int skipLow,
      int skipHigh) {
    startTimes = new long[repeats];
    graphGenerationTimes = new long[repeats];
    endTimes = new long[repeats];

    for (int i = 0; i < repeats; ++i) {
      startTimes[i] = System.nanoTime();
      scenario.generateGraph();
      graphGenerationTimes[i] = System.nanoTime();
      scenario.runShortestPath();
      endTimes[i] = System.nanoTime();
    }

    double[] times = new double[repeats];

    double averageShortestPathTime = 0.0;
    for (int i = 0; i < repeats; ++i) {
      double time = (endTimes[i] - graphGenerationTimes[i]) / 1000000.0;
      averageShortestPathTime += time;
      times[i] = time;
      if (printOutInnerResults) {
        System.out.println(
            ""
                + i
                + ". run: "
                + startTimes[i]
                + ","
                + graphGenerationTimes[i]
                + ","
                + endTimes[i]
                + "->"
                + (endTimes[i] - graphGenerationTimes[i]) / 1000000.0);
      }
    }
    averageShortestPathTime /= repeats;

    Arrays.sort(times);

    double averageShortestPathWithoutExrtremes = 0.0;
    for (int i = skipLow; i < repeats - skipHigh; ++i) {
      averageShortestPathWithoutExrtremes += times[i];
    }
    averageShortestPathWithoutExrtremes /= repeats - skipHigh - skipLow;

    if (printAverageTimes) {
      System.out.println(
          scenario.getScenarioName() + " - AverageShortestPathTime: " + averageShortestPathTime);
      System.out.println(
          scenario.getScenarioName()
              + " - AverageShortestPathTimeWithoutExtremes: "
              + averageShortestPathWithoutExrtremes);
      try {
        outputLineToCsv(
            new CsvLine(
                scenario.getScenarioName(),
                scenario.getSize(),
                scenario.getArcs(),
                averageShortestPathTime,
                averageShortestPathWithoutExrtremes));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return averageShortestPathWithoutExrtremes;
  }

  public void outputLineToCsv(CsvLine line) throws IOException {
    FileWriter out = new FileWriter(scenario.getFileName(), true);
    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withDelimiter(';'))) {
      printer.printRecord(line.getLineValues());
    }
  }
}
