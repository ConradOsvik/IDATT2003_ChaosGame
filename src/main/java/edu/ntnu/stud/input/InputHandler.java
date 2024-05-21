package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

/**
 * The InputHandler class is responsible for handling user input.
 * It uses an instance of PrimitiveInputHandler to get primitive types of input.
 */
class InputHandler {
  /**
   * The PrimitiveInputHandler used to get primitive types of input.
   */
  private final PrimitiveInputHandler primitiveInputHandler;

  /**
   * Constructor for the InputHandler class.
   * Initializes the PrimitiveInputHandler instance.
   */
  public InputHandler() {
    this.primitiveInputHandler = new PrimitiveInputHandler();
  }

  /**
   * Gets a string input from the user.
   * @return The string input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public String getString() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getString();
  }

  /**
   * Gets a transform type input from the user.
   * The transform type must be either 'affine' or 'julia'.
   * @return The transform type input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is not 'affine' or 'julia'.
   */
  public String getTransformType() throws InvalidFormatException, InvalidInputException {
    String input = primitiveInputHandler.getString();

    if (!input.equalsIgnoreCase("affine") && !input.equalsIgnoreCase("julia")) {
      throw new InvalidInputException("The transform type must be either 'affine' or 'julia'.");
    }

    return input.toLowerCase();
  }

  /**
   * Gets an integer input from the user.
   * @return The integer input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public int getInt() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getInt();
  }

  /**
   * Gets a sign input from the user.
   * The sign must be either 1 or -1.
   * @return The sign input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is not 1 or -1.
   */
  public int getSign() throws InvalidFormatException, InvalidInputException {
    int input = primitiveInputHandler.getInt();

    if (input != 1 && input != -1) {
      throw new InvalidInputException("The sign must be either 1 or -1.");
    }

    return input;
  }

  /**
   * Gets a double input from the user.
   * @return The double input from the user.
   * @throws InvalidFormatException If the format of the input is invalid.
   * @throws InvalidInputException If the input is logically invalid.
   */
  public double getDouble() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getDouble();
  }
}
