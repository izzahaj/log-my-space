package com.logmyspace.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** The main entry point to the Log My Space API. */
@SpringBootApplication
public class LogmyspaceApiApplication {

  /**
   * The application's main method. Used to launch the Log My Space API.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(LogmyspaceApiApplication.class, args);
  }
}
