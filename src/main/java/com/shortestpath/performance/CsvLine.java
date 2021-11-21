package com.shortestpath.performance;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvLine {
  private final String scenarioName;
  private final int size;
  private final int arcs;
  private final double average;
  private final double averageWithoutExtremes;
  private final DecimalFormat formatter;

  public CsvLine(
      String scenarioName, int size, int arcs, double average, double averageWithoutExtremes) {
    this.scenarioName = scenarioName;
    this.size = size;
    this.arcs = arcs;
    this.average = average;
    this.averageWithoutExtremes = averageWithoutExtremes;
    this.formatter = createFormatter();
  }

  private DecimalFormat createFormatter() {
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
    otherSymbols.setDecimalSeparator(',');
    otherSymbols.setGroupingSeparator('.');
    return new DecimalFormat("0.000000000", otherSymbols);
  }

  public List<String> getLineValues() {
    List<String> lineValues = new ArrayList<>();
    lineValues.add(scenarioName);
    lineValues.add(String.valueOf(size));
    lineValues.add(String.valueOf(arcs));
    lineValues.add(formatter.format(average));
    lineValues.add(formatter.format(averageWithoutExtremes));
    return lineValues;
  }

  public static List<String> csvHeaders() {
    List<String> headers = new ArrayList<>();
    headers.add("Scenario Name");
    headers.add("Size");
    headers.add("Arcs");
    headers.add("Average");
    headers.add("Average Without Extremes");
    return headers;
  }
}
