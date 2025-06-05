package com.logmyspace.api.commons.util;

/**
 * Utility class for App specific helper methods.
 */
public class AppUtil {

  /**
   * Validates that the given condition is {@code true}.
   *
   * <p>This method is typically used for validating method arguments</p>
   *
   * @param condition    the condition to validate
   * @param errorMessage the error message to include in the exception if validation fails
   * @throws IllegalArgumentException if {@code condition} is {@code false}
   */
  public static void validateArgument(Boolean condition, String errorMessage) {
    if (!condition) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
