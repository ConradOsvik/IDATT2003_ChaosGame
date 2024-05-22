package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.Cli;

/**
 * This class represents a group of commands that can be executed from the command line interface
 * (CLI). It extends the abstract CommandGroup class and adds specific commands to the group. Each
 * command represents a different action that can be performed by the CLIController.
 *
 * @see edu.ntnu.stud.commands.CommandGroup
 */
public class CLICommandGroup extends CommandGroup {

  /**
   * Constructs a new CLICommandGroup with a set of specific commands. The commands are added to the
   * group in the constructor.
   *
   * @param controller the CLIController that controls the command line interface
   * @param cli the command line interface
   * @param validatedInput the input validator
   */
  public CLICommandGroup(CLIController controller, Cli cli, ValidatedInput validatedInput) {
    super("Select a command:");

    this.addCommand(new ReadFileCommand(controller, cli, validatedInput));
    this.addCommand(new WriteToFileCommand(cli, validatedInput));
    this.addCommand(new RunStepsCommand(controller, cli, validatedInput));
    this.addCommand(new PrintFractalCommand(controller));
    this.addCommand(new ExitCommand(cli));
  }
}
