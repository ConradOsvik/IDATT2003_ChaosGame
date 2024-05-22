package edu.ntnu.stud.views;

import edu.ntnu.stud.commands.CliCommandGroup;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.utils.Color;
import java.util.List;

/**
 * This class represents a Command Line Interface (CLI) for displaying menus and messages to the
 * user. It includes methods for displaying a menu, a message, and an error message.
 */
public class Cli {
  // This class is the Command Line Interface (CLI) for displaying menus and messages to the user

  /**
   * Displays the menu of commands to the user.
   *
   * @param commands the CLICommandGroup containing the commands to display
   */
  public void displayMenu(CliCommandGroup commands) {
    System.out.println(commands.getTitle());
    List<Command> commandList = commands.getCommands();
    for (int i = 0; i < commandList.size(); i++) {
      System.out.println(
          Color.colorString(Integer.toString(i + 1), Color.WHITE)
              + ": "
              + Color.colorString(commandList.get(i).getName(), Color.CYAN)
              + " - "
              + commandList.get(i).getDescription());
    }
  }

  /**
   * Displays a message to the user.
   *
   * @param message the message to display
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Displays an error message to the user.
   *
   * @param message the error message to display
   */
  public void displayErrorMessage(String message) {
    System.err.println(message);
  }
}
