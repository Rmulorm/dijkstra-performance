package com.shortestpath.main;

import java.io.IOException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerformanceTestApplication {

  public static void main(String[] args) {
    try {
      new GraphSizeAnalysisMain().run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
