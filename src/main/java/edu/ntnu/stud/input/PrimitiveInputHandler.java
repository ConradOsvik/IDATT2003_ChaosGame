package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import java.util.Scanner;

/**
 * The PrimitiveInputHandler class is responsible for handling primitive types of user input.
 * It uses a Scanner instance to read the input from the user.
 */
class PrimitiveInputHandler {

  /**
   * The Scanner instance used to read input from the user.
   */
  private final Scanner scanner;

  /**
   * Constructor for the PrimitiveInputHandler class.
   * Initializes the Scanner instance.
   */
  public PrimitiveInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Wraps the supplier's get method with exception handling.
   * @param supplier The supplier whose get method is to be wrapped.
   * @return The result of the supplier's get method.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  private <T> T wrap(ThrowingSupplier<T> supplier)
      throws InvalidFormatException, InvalidInputException {
    try {
      return supplier.get();
    } catch (InvalidInputException e) {
      throw new InvalidInputException(e.getMessage());
    } catch (Exception e) {
      throw new InvalidFormatException();
    }
  }

  /**
   * Checks if the input string is empty.
   * @param input The input string to check.
   * @throws InvalidInputException If the input string is empty.
   */
  private void isEmpty(String input) throws InvalidInputException {
    if (input.isEmpty()) {
      throw new InvalidInputException("Input cannot be empty, please try again.");
    }
  }

  /**
   * Gets a string input from the user.
   * @return The string input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public String getString() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return input;
        });
  }

  /**
   * Gets an integer input from the user.
   * @return The integer input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public int getInt() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return Integer.parseInt(input);
        });
  }

  /**
   * Gets a double input from the user.
   * @return The double input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public double getDouble() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return Double.parseDouble(input);
        });
  }
}
