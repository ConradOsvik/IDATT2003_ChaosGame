package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.views.CLI;

public class ValidatedInput {
  private final CLI cli;
  private final InputHandler inputHandler;
  public ValidatedInput(CLI cli){
    this.cli = cli;
    this.inputHandler = new InputHandler();
  }
  public <T> T execute(ThrowingSupplier<T> supplier, String prompt){
    while(true){
      cli.displayMessage(prompt);
      try{
        return supplier.get();
      } catch (InvalidFormatException | InvalidInputException e){
        cli.displayErrorMessage(e.getMessage());
      }
    }
  }

  public String getString(String prompt){
    return execute(inputHandler::getString, prompt);
  }

  public String getTransformType(String prompt){
    return execute(inputHandler::getTransformType, prompt);
  }

  public int getInt(String prompt){
    return execute(inputHandler::getInt, prompt);
  }

  public int getSign(String prompt){
    return execute(inputHandler::getSign, prompt);
  }

  public double getDouble(String prompt){
    return execute(inputHandler::getDouble, prompt);
  }

}
