package edu.ntnu.stud.views;

import edu.ntnu.stud.commands.CLICommandGroup;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.utils.Color;
import java.util.List;

public class Cli {
  public void displayMenu(CLICommandGroup commands) {
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

  public void displayMessage(String message) {
    System.out.println(message);
  }

  public void displayErrorMessage(String message) {
    System.err.println(message);
  }
}
