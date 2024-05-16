package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.CLI;

public class CLICommandGroup extends CommandGroup {
  public CLICommandGroup(CLIController controller, CLI cli, ValidatedInput validatedInput) {
    super("Select a command:");

    this.addCommand(new ReadFileCommand(controller, cli, validatedInput));
    this.addCommand(new WriteToFileCommand(cli, validatedInput));
    this.addCommand(new RunStepsCommand(controller, cli, validatedInput));
    this.addCommand(new PrintFractalCommand(controller));
    this.addCommand(new ExitCommand(cli));
  }
}
