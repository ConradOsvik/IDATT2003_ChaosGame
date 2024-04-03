package edu.ntnu.stud.commands;

public class GoBackCommand implements Command {
  private final CommandGroup parentCommandGroup;
  public GoBackCommand(CommandGroup parentCommandGroup){
    this.parentCommandGroup = parentCommandGroup;
  }
  @Override
  public String getName() {
    return "Go back";
  }
  @Override
  public String getDescription() {
    return "Go back to the previous state";
  }
  @Override
  public void execute(){
  }
}
