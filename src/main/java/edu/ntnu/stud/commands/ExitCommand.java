package edu.ntnu.stud.commands;

import edu.ntnu.stud.views.CLI;

public class ExitCommand implements Command{
  private final CLI cli;

  public ExitCommand(CLI cli){
    this.cli = cli;
  }
  @Override
  public String getName() {
    return "Exit";
  }

  @Override
  public String getDescription() {
    return "Exit the program";
  }

  @Override
  public void execute() {
    cli.displayMessage("Exiting program...");
    System.exit(0);
  }
}
