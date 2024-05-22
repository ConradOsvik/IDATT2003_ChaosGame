package edu.ntnu.stud.controllers;

import edu.ntnu.stud.commands.CLICommandGroup;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGame;
import edu.ntnu.stud.views.Cli;
/**
 * This class represents the controller for the command line interface (CLI).
 * It is used to handle user input and execute commands.
 */
public class CLIController {
  private final Cli cli;
  private ChaosGame chaosGame;
  private final CLICommandGroup commands;
  private final ValidatedInput validatedInput;

  /**
   * Constructs a new CLIController with the specified CLI.
   *
   * @param cli the command line interface
   */
  public CLIController(Cli cli) {
    this.cli = cli;
    this.validatedInput = new ValidatedInput(cli);
    this.commands = new CLICommandGroup(this, this.cli, this.validatedInput);
  }

  /**
   * Handles the user request by displaying the menu and executing the selected command.
   */
  public void handleRequest() {
    cli.displayMenu(commands);
    int choice = validatedInput.getInt("Enter your choice:");

    if (choice < 1 || choice > commands.getCommands().size()) {
      cli.displayErrorMessage("Invalid choice");
      return;
    }

    Command command = commands.getCommands().get(choice - 1);
    command.execute();
  }

  /**
   * Sets the ChaosGame to be used by the controller.
   *
   * @param chaosGame the ChaosGame to be used
   */
  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * Runs the specified number of steps in the ChaosGame.
   *
   * @param steps the number of steps to run
   */
  public void runSteps(int steps) {
    if (chaosGame == null) {
      cli.displayErrorMessage("No ChaosGame loaded");
      return;
    }

    chaosGame.runSteps(steps);
    cli.displayMessage(steps + " steps executed");
  }

  /**
   * Prints the fractal to the console.
   */
  public void printFractal() {
    if (chaosGame == null) {
      cli.displayErrorMessage("No ChaosGame loaded");
      return;
    }

    cli.displayMessage("Fractal: ");
    int[][] canvas = chaosGame.getCanvas().getCanvas();
    for (int[] canvasRow : canvas) {
      for (int i : canvasRow) {
        System.out.print(i == 0 ? ' ' : 'X');
      }
      System.out.println();
    }
  }
}
