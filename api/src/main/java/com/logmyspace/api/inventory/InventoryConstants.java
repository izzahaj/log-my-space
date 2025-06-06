package com.logmyspace.api.inventory;

/** Contains constants related to the {@link Inventory} domain. */
public class InventoryConstants {
  public static final int MIN_NAME_LENGTH = 1;
  public static final int MAX_NAME_LENGTH = 30;
  public static final String NAME_LENGTH_MESSAGE =
      "Inventory name must be between {min} and {max} characters (inclusive)";
  public static final String BLANK_NAME_MESSAGE = "Inventory name cannot be blank";
  public static final String DUPLICATE_NAME_MESSAGE = "Inventory name already exists for this user";
}
