package com.logmyspace.api.user;

/** Contains constants related to the {@link User} domain. */
public class UserConstants {
  public static final int MIN_USERNAME_LENGTH = 5;
  public static final int MAX_USERNAME_LENGTH = 30;
  public static final String USERNAME_REGEX = "^[a-zA-Z0-9@^$.!`#+'~_-]*$";
  private static final String[] ALLOWED_SPECIAL_CHARACTERS = {
    "@", "^", "$", ".", "!", "`", "#", "+", "'", "~", "_", "-"
  };
  private static final String BLANK_USERNAME_MESSAGE = "Username cannot be blank.";
  private static final String USERNAME_LENGTH_MESSAGE =
      String.format(
          "Username must be between %d and %d characters.",
          MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH);
  private static final String USERNAME_PATTERN_MESSAGE =
      String.format(
          "Username must be alphanumeric, and can contain the following special characters: %s.",
          String.join(", ", ALLOWED_SPECIAL_CHARACTERS));
  private static final String BLANK_EMAIL_MESSAGE = "Email cannot be blank.";
  private static final String EMAIL_FORMAT_MESSAGE = "Invalid email format.";
}
