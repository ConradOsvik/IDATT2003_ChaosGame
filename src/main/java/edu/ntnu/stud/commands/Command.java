package edu.ntnu.stud.commands;

public interface Command {
  String getName();

  String getDescription();

  void execute();
}
