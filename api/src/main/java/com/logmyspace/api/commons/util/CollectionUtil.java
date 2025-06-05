package com.logmyspace.api.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utility class providing helper method for collections.
 */
public class CollectionUtil {
  /**
   * Ensures that the provided collection and all of its elements are not {@code null}.
   *
   * @param collection the collection to check
   * @throws NullPointerException if the collection itself or any of its elements are {@code null}
   */
  public static void requireAllNonNull(Collection<?> collection) {
    requireNonNull(collection);
    collection.forEach(Objects::requireNonNull);
  }

  /**
   * Ensures that all the provided objects are not {@code null}.
   *
   * @param items the objects to check
   * @throws NullPointerException if the array itself or any of its elements are {@code null}
   */
  public static void requireAllNonNull(Object... items) {
    requireNonNull(items);
    Stream.of(items).forEach(Objects::requireNonNull);
  }

  /**
   * Trims leading and trailing whitespace from each non-null string in the provided list.
   *
   * <p>The list is modified in-place.</p>
   *
   * @param list the list of strings to trim
   * @return the same list instance, with trimmed strings
   * @throws NullPointerException if the list is {@code null}
   */
  public static List<String> stripList(List<String> list) {
    requireNonNull(list);
    for (int i = 0; i < list.size(); i++) {
      String str = list.get(i);
      if (str != null) {
        String strippedString = list.get(i).strip();
        list.set(i, strippedString);
      }
    }
    return list;
  }

  /**
   * Validates that the provided collection is not {@code null} or empty.
   *
   * @param collection the collection to validate
   * @throws NullPointerException     if the collection is {@code null}
   * @throws IllegalArgumentException if the collection is empty
   */
  public static void validateCollectionNotEmpty(Collection<?> collection) {
    requireNonNull(collection);
    if (collection.isEmpty()) {
      throw new IllegalArgumentException("Collection must not be empty.");
    }
  }
}
