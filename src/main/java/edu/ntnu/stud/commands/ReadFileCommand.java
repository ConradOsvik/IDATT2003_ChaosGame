package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.exceptions.FileHandlingException;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGame;
import edu.ntnu.stud.models.ChaosGameDescription;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.views.CLI;

/**
 * This class represents the read file command in the CLI. It implements the Command interface and
 * is used to read a file and load a ChaosGame.
 */
public class ReadFileCommand implements Command {

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
   * Constructs a new ReadFileCommand with the specified CLIController, CLI, and ValidatedInput.
   *
   * @param controller     the CLIController to be used by this command
   * @param cli            the CLI to be used by this command
   * @param validatedInput the ValidatedInput to be used by this command
   */
  public ReadFileCommand(CLIController controller, CLI cli, ValidatedInput validatedInput) {
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
    return "Read file";
  }

  /**
   * Returns the description of this command.
   *
   * @return the description of this command
   */
  @Override
  public String getDescription() {
    return "Reads a file";
  }

  /**
   * Executes this command. Reads a file and loads a ChaosGame. Displays an error message if the
   * file cannot be read.
   */
  @Override
  public void execute() {
    cli.displayMessage("Executing command: " + getName());
    String fileName = validatedInput.getString("Enter the name of the file to read: ");
    String path = "src/main/resources/transforms/" + fileName + ".txt";

    try {
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(path);
      ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 40, 40);
      controller.setChaosGame(chaosGame);
      cli.displayMessage("File read successfully");
    } catch (FileHandlingException e) {
      cli.displayErrorMessage("Error reading file: " + e.getMessage());
    }
  }
}
