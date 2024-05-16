package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.exceptions.FileHandlingException;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.ChaosGame;
import edu.ntnu.stud.models.ChaosGameDescription;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.views.CLI;

public class ReadFileCommand implements Command {
  private final CLIController controller;
  private final CLI cli;
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final ValidatedInput validatedInput;

  public ReadFileCommand(CLIController controller, CLI cli, ValidatedInput validatedInput) {
    this.controller = controller;
    this.cli = cli;
    this.validatedInput = validatedInput;
    this.chaosGameFileHandler = new ChaosGameFileHandler();
  }

  @Override
  public String getName() {
    return "Read file";
  }

  @Override
  public String getDescription() {
    return "Reads a file";
  }

  @Override
  public void execute() {
    cli.displayMessage("Executing command: " + getName());
    String fileName = validatedInput.getString("Enter the name of the file to read: ");
    String path = "src/main/resources/" + fileName + ".txt";

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
