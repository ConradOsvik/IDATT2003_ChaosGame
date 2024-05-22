package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CliController;

/**
 * This class represents the print fractal command in the CLI. It implements the Command interface
 * and is used to print the fractal to the console.
 */
public class PrintFractalCommand implements Command {
  /** The CLIController to be used by the command. */
  private final CliController controller;

  /**
   * Constructs a new PrintFractalCommand with the specified CLIController.
   *
   * @param controller the CLIController to be used by this command
   */
  public PrintFractalCommand(CliController controller) {
    this.controller = controller;
  }

  /**
   * Returns the name of this command.
   *
   * @return the name of this command
   */
  @Override
  public String getName() {
    return "Print fractal";
  }

  /**
   * Returns the description of this command.
   *
   * @return the description of this command
   */
  @Override
  public String getDescription() {
    return "Prints the fractal to the console";
  }

  /** Executes this command. Prints the fractal to the console. */
  @Override
  public void execute() {
    System.out.println("Executing command: " + getName());
    controller.printFractal();
  }
}
