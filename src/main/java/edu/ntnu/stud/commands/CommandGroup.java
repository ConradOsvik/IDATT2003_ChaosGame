package edu.ntnu.stud.commands;

import java.util.ArrayList;
import java.util.List;

abstract class CommandGroup {
  private final String title;
  private final List<Command> commands;

  public CommandGroup(String title){
    this.title = title;
    this.commands = new ArrayList<>();
  }

  public CommandGroup(String title, CommandGroup parentCommandGroup){
    this.title = title;
    this.commands = new ArrayList<>();
    if(parentCommandGroup != null){
      commands.add(new GoBackCommand(parentCommandGroup));
    }
  }

  public String getTitle() {
    return title;
  }

  public void addCommand(Command command){
    commands.add(command);
  }

  public List<Command> getCommands() {
    return commands;
  }
}