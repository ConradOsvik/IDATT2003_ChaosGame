package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

class InputHandler {
  private final PrimitiveInputHandler primitiveInputHandler;

  public InputHandler() {
    this.primitiveInputHandler = new PrimitiveInputHandler();
  }

  public String getString()
      throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getString();
  }

  public int getInt() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getInt();
  }

  public double getDouble() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getDouble();
  }

  public double getSign() throws InvalidFormatException, InvalidInputException {
    double input = primitiveInputHandler.getDouble();

    if (input != 1 && input != -1) {
      throw new InvalidInputException("The sign must be either 1 or -1.");
    }

    return input;
  }
}
