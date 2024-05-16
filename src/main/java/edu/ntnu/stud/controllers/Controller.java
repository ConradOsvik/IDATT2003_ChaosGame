package edu.ntnu.stud.controllers;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observer;

public abstract class Controller implements Observer {

  @Override
  public void update(Event event) {}

  @Override
  public void update(Event event, Object data) {}
}
