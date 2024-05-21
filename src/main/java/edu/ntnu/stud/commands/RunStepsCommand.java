package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.views.CLI;

/**
 * This class represents the run steps command in the CLI.
 * It implements the Command interface and is used to run a specified number of steps in the ChaosGame.
 */
public class RunStepsCommand implements Command {

  /**
   * The CLIController to be used by the command.
   */
  private final CLIController controller;

  /**
   * The CLI to be used by the command.
   */
  private final CLI cli;

  /**
   * The ChaosGameFileHandler to be used by the command.
   */
  private final ChaosGameFileHandler chaosGameFileHandler;

  /**
   * The ValidatedInput to be used by the command.
   */
  private final ValidatedInput validatedInput;

  /**
   * Constructs a new RunStepsCommand with the specified CLIController, CLI, and ValidatedInput.
   *
   * @param controller     the CLIController to be used by this command
   * @param cli            the CLI to be used by this command
   * @param validatedInput the ValidatedInput to be used by this command
   */
  public RunStepsCommand(CLIController controller, CLI cli, ValidatedInput validatedInput) {
    this.controller = controller;
    this.cli = cli;
    this.validatedInput = validatedInput;
    this.chaosGameFileHandler = new ChaosGameFileHandler();
  }

  /**
   * Returns the name of this command.
   *
   * @return the name of this command
   */
  @Override
  public String getName() {
    return "Run steps";
  }

  /**
   * Returns the description of this command.
   *
   * @return the description of this command
   */
  @Override
  public String getDescription() {
    return "Runs the steps";
  }

  /**
   * Executes this command. Runs a specified number of steps in the ChaosGame.
   */
  @Override
  public void execute() {
    System.out.println("Executing command: " + getName());
    int steps = validatedInput.getInt("Enter number of steps: ");
    controller.runSteps(steps);
  }
}
