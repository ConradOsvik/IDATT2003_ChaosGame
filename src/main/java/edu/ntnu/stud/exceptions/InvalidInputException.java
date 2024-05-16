package edu.ntnu.stud.exceptions;

/** This class represents an exception thrown when the input is logically invalid. */
public class InvalidInputException extends Exception {

  private static final String ERROR_MESSAGE =
      "The input you provided" + " is invalid, please try again:";

  /**
   * Initializes the InvalidInputException calling super with the predefined error message to
   * display.
   */
  public InvalidInputException() {
    super(ERROR_MESSAGE);
  }

  /**
   * Initializes the InvalidInputException calling super with the provided error message to display.
   *
   * @param message The error message to display.
   */
  public InvalidInputException(String message) {
    super(message);
  }
}
