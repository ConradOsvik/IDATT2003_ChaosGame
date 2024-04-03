package edu.ntnu.stud.exceptions;

/**
 * This class represents a custom exception that is thrown when there is an error in file handling.
 * It extends the Exception class and provides two constructors for different use cases.
 */
public class FileHandlingException extends Exception {
  private static final String ERROR_MESSAGE = "An error occurred while handling the file";

  /**
   * Default constructor for the FileHandlingException class. It uses a predefined error message.
   */
  public FileHandlingException() {
    super(ERROR_MESSAGE);
  }

  /**
   * Constructor for the FileHandlingException class that accepts a custom error message.
   *
   * @param message The custom error message to be used.
   */
  public FileHandlingException(String message) {
    super(message);
  }
}
