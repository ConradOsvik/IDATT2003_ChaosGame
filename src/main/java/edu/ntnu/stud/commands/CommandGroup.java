package edu.ntnu.stud.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a group of commands. It provides methods to add commands to the group,
 * retrieve the list of commands, and get the title of the command group.
 */
abstract class CommandGroup {
  /**
   * The title of the command group. This is displayed to the user in the UI.
   */
  private final String title;
  /**
   * The list of commands in the command group.
   */
  private final List<Command> commands;

  /**
   * Constructs a new CommandGroup with the specified title.
   *
   * @param title the title of the command group
   */
  public CommandGroup(String title) {
    this.title = title;
    this.commands = new ArrayList<>();
  }

  /**
   * Returns the title of the command group.
   *
   * @return the title of the command group
   */
  public String getTitle() {
    return title;
  }

  /**
   * Adds a command to the command group.
   *
   * @param command the command to add
   */
  public void addCommand(Command command) {
    commands.add(command);
  }

  /**
   * Returns the list of commands in the command group.
   *
   * @return the list of commands in the command group
   */
  public List<Command> getCommands() {
    return commands;
  }
}
