package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

/**
 * The ThrowingSupplier interface is a functional interface that represents a supplier of results.
 * Unlike a regular Supplier, a ThrowingSupplier can throw an exception.
 *
 * @param <T> the type of results supplied by this supplier
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {
  /**
   * Gets a result.
   *
   * @return a result
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  T get() throws InvalidFormatException, InvalidInputException;
}
