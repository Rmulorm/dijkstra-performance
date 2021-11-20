package com.shortestpath.performance;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvLine {
  private final String scenarioName;
  private final double p;
  private final int size;
  private final double average;
  private final double averageWithoutExtremes;
  private final DecimalFormat formatter;

  public CsvLine(
      String scenarioName,double p, int size, double average, double averageWithoutExtremes) {
    this.scenarioName = scenarioName;
    this.p = p;
    this.size = size;
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
    lineValues.add(Double.toString(p));
    lineValues.add(String.valueOf(size));
    lineValues.add(formatter.format(average));
    lineValues.add(formatter.format(averageWithoutExtremes));
    return lineValues;
  }

  public static String[] csvHeaders() {
    return new String[] {"Scenario Name", "Size", "P", "Average", "Average Without Extremes"};
  }
}
