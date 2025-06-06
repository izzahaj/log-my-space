package com.logmyspace.api.commons.exceptions;

/** Thrown when a requested resource is not found. */
public class ResourceNotFoundException extends RuntimeException {
  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
