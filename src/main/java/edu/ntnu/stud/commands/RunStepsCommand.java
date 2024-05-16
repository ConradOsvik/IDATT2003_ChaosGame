package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.views.CLI;

public class RunStepsCommand implements Command {
  private final CLIController controller;
  private final CLI cli;
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final ValidatedInput validatedInput;

  public RunStepsCommand(CLIController controller, CLI cli, ValidatedInput validatedInput) {
    this.controller = controller;
    this.cli = cli;
    this.validatedInput = validatedInput;
    this.chaosGameFileHandler = new ChaosGameFileHandler();
  }

  @Override
  public String getName() {
    return "Run steps";
  }

  @Override
  public String getDescription() {
    return "Runs the steps";
  }

  @Override
  public void execute() {
    System.out.println("Executing command: " + getName());
    int steps = validatedInput.getInt("Enter number of steps: ");
    controller.runSteps(steps);
  }
}
