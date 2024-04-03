package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

@FunctionalInterface
public interface ThrowingSupplier<T> {
  T get() throws InvalidFormatException, InvalidInputException;
}