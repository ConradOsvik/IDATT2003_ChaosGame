package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.views.CLI;

/**
 * The ValidatedInput class is responsible for handling user input validation.
 * It uses an instance of InputHandler to get the input and a CLI instance to display messages.
 */
public class ValidatedInput {

  /**
   * The CLI instance used to display messages.
   */
  private final CLI cli;

  /**
   * The InputHandler instance used to get the input.
   */
  private final InputHandler inputHandler;

  /**
   * Constructor for the ValidatedInput class.
   * Initializes the CLI and InputHandler instances.
   *
   * @param cli The CLI instance used to display messages.
   */
  public ValidatedInput(CLI cli) {
    this.cli = cli;
    this.inputHandler = new InputHandler();
  }

  /**
   * Executes the supplier's get method with exception handling and a prompt.
   *
   * @param supplier The supplier whose get method is to be executed.
   * @param prompt The prompt to display before getting the input.
   * @return The result of the supplier's get method.
   */
  public <T> T execute(ThrowingSupplier<T> supplier, String prompt) {
    while (true) {
      cli.displayMessage(prompt);
      try {
        return supplier.get();
      } catch (InvalidFormatException | InvalidInputException e) {
        cli.displayErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Gets a string input from the user with validation.
   *
   * @param prompt The prompt to display before getting the input.
   * @return The string input from the user.
   */
  public String getString(String prompt) {
    return execute(inputHandler::getString, prompt);
  }

  /**
   * Gets a transform type input from the user with validation.
   *
   * @param prompt The prompt to display before getting the input.
   * @return The transform type input from the user.
   */
  public String getTransformType(String prompt) {
    return execute(inputHandler::getTransformType, prompt);
  }

  /**
   * Gets an integer input from the user with validation.
   *
   * @param prompt The prompt to display before getting the input.
   * @return The integer input from the user.
   */
  public int getInt(String prompt) {
    return execute(inputHandler::getInt, prompt);
  }

  /**
   * Gets a sign input from the user with validation.
   *
   * @param prompt The prompt to display before getting the input.
   * @return The sign input from the user.
   */
  public int getSign(String prompt) {
    return execute(inputHandler::getSign, prompt);
  }

  /**
   * Gets a double input from the user with validation.
   *
   * @param prompt The prompt to display before getting the input.
   * @return The double input from the user.
   */
  public double getDouble(String prompt) {
    return execute(inputHandler::getDouble, prompt);
  }
}
