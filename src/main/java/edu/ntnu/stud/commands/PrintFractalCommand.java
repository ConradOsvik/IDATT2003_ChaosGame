package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;

public class PrintFractalCommand implements Command {
  private final CLIController controller;

  public PrintFractalCommand(CLIController controller) {
    this.controller = controller;
  }

  @Override
  public String getName() {
    return "Print fractal";
  }

  @Override
  public String getDescription() {
    return "Prints the fractal to the console";
  }

  @Override
  public void execute() {
    System.out.println("Executing command: " + getName());
    controller.printFractal();
  }
}
