package com.shortestpath.performance;

import java.util.ArrayList;
import java.util.List;

public class CsvLine {
  private String scenarioName;
  private int size;
  private double p;
  private double average;
  private double averageWithoutExtremes;

  public CsvLine(String scenarioName, int size, double p, double average, double averageWithoutExtremes) {
    this.scenarioName = scenarioName;
    this.size = size;
    this.p = p;
    this.average = average;
    this.averageWithoutExtremes = averageWithoutExtremes;
  }

  public List<String > getLineValues() {
    List<String> lineValues = new ArrayList<>();
    lineValues.add(scenarioName);
    lineValues.add(String.valueOf(size));
    lineValues.add(String.valueOf(p));
    lineValues.add(String.valueOf(average));
    lineValues.add(String.valueOf(averageWithoutExtremes));
    return lineValues;
  }

  public static String[] csvHeaders() {
    return new String[] {"Scenario Name", "Size", "P", "Average", "Average Without Extremes"};
  }
}
