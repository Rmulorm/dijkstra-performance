package com.shortestpath.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerformanceTestApplication {

  public static void main(String[] args) {
    new GraphSizeAnalysisMain().run();
  }
}
