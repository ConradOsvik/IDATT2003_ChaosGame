package edu.ntnu.stud.commands;

import edu.ntnu.stud.views.Cli;

/** This class represents the exit command in the CLI. It implements the Command interface. */
public class ExitCommand implements Command {

  /** The CLI to be used by this command. */
  private final Cli cli;

  /**
   * Constructs a new ExitCommand with the specified CLI.
   *
   * @param cli the CLI to be used by this command
   */
  public ExitCommand(Cli cli) {
    this.cli = cli;
  }

  /**
   * Returns the name of this command.
   *
   * @return the name of this command
   */
  @Override
  public String getName() {
    return "Exit";
  }

  /**
   * Returns the description of this command.
   *
   * @return the description of this command
   */
  @Override
  public String getDescription() {
    return "Exit the program";
  }

  /** Executes this command. Displays a message and exits the program. */
  @Override
  public void execute() {
    cli.displayMessage("Exiting program...");
    System.exit(0);
  }
}
