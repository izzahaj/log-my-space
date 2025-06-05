package com.logmyspace.api.user.validators;

import static com.logmyspace.api.commons.util.AppUtil.validateArgument;
import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.UUID;
import org.apache.commons.validator.routines.EmailValidator;

public class UserValidator {
  public static final int MINIMUM_USERNAME_LENGTH = 5;
  public static final int MAXIMUM_USERNAME_LENGTH = 30;
  public static final String USERNAME_REGEX = "^[a-zA-Z0-9@^$.!`#+'~_-]*$";
  private static final String[] ALLOWED_SPECIAL_CHARACTERS = {
    "@", "^", "$", ".", "!", "`", "#", "+", "'", "~", "_", "-"
  };
  private static final String BLANK_USERNAME_MESSAGE = "Username cannot be blank.";
  private static final String USERNAME_LENGTH_MESSAGE =
      String.format(
          "Username must be between %d and %d characters.",
          MINIMUM_USERNAME_LENGTH, MAXIMUM_USERNAME_LENGTH);
  private static final String USERNAME_PATTERN_MESSAGE =
      String.format(
          "Username must be alphanumeric, and can contain the following special characters: %s.",
          String.join(", ", ALLOWED_SPECIAL_CHARACTERS));
  private static final String BLANK_EMAIL_MESSAGE = "Email cannot be blank.";
  private static final String EMAIL_FORMAT_MESSAGE = "Invalid email format.";

  public static void validateUser(UUID id, String username, String email) {
    requireAllNonNull(id, username, email);
    validateUsername(username);
    validateEmail(email);
  }

  public static void validateUsername(String username) {
    requireNonNull(username);
    boolean isValidLength =
        username.length() >= MINIMUM_USERNAME_LENGTH
            && username.length() <= MAXIMUM_USERNAME_LENGTH;
    boolean isValidPattern = username.matches(USERNAME_REGEX);

    validateArgument(!username.isBlank(), BLANK_USERNAME_MESSAGE);
    validateArgument(isValidLength, USERNAME_LENGTH_MESSAGE);
    validateArgument(isValidPattern, USERNAME_PATTERN_MESSAGE);
  }

  public static void validateEmail(String email) {
    requireNonNull(email);
    boolean isValidFormat = EmailValidator.getInstance().isValid(email);

    validateArgument(!email.isBlank(), BLANK_EMAIL_MESSAGE);
    validateArgument(isValidFormat, EMAIL_FORMAT_MESSAGE);
  }
}
