package edu.ntnu.stud.exceptions;

/**
 * This class represents an exception thrown when the input has an invalid format.
 */
public class InvalidFormatException extends Exception {

  private static final String ERROR_MESSAGE = "The input you provided has an"
      + " invalid format, please try again:";

  /**
   * Initializes the InvalidFormatException calling super with the predefined error message to
   * display.
   */
  public InvalidFormatException() {
    super(ERROR_MESSAGE);
  }

  /**
   * Initializes the InvalidFormatException calling super with the provided error message to
   * display.
   *
   * @param message The error message to display.
   */
  public InvalidFormatException(String message) {
    super(message);
  }
}