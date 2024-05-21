package edu.ntnu.stud.controllers;

import edu.ntnu.stud.commands.CLICommandGroup;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGame;
import edu.ntnu.stud.views.CLI;

public class CLIController {
  private final CLI cli;
  private ChaosGame chaosGame;
  private final CLICommandGroup commands;
  private final ValidatedInput validatedInput;

  public CLIController(CLI cli) {
    this.cli = cli;
    this.validatedInput = new ValidatedInput(cli);
    this.commands = new CLICommandGroup(this, this.cli, this.validatedInput);
  }

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

  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  public void runSteps(int steps) {
    if (chaosGame == null) {
      cli.displayErrorMessage("No ChaosGame loaded");
      return;
    }

    chaosGame.runSteps(steps);
    cli.displayMessage(steps + " steps executed");
  }

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
