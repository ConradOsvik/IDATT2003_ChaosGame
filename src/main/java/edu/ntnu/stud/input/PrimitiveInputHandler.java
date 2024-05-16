package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import java.util.Scanner;

class PrimitiveInputHandler {
  private final Scanner scanner;

  public PrimitiveInputHandler() {
    this.scanner = new Scanner(System.in);
  }

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

  private void isEmpty(String input) throws InvalidInputException {
    if (input.isEmpty()) {
      throw new InvalidInputException("Input cannot be empty, please try again.");
    }
  }

  public String getString() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return input;
        });
  }

  public int getInt() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return Integer.parseInt(input);
        });
  }

  public double getDouble() throws InvalidFormatException, InvalidInputException {
    return wrap(
        () -> {
          String input = scanner.nextLine();
          isEmpty(input);
          return Double.parseDouble(input);
        });
  }
}
