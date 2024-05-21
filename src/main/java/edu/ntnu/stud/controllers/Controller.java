package edu.ntnu.stud.controllers;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observer;

abstract class Controller implements Observer {

  @Override
  public void update(Event event) {}

  @Override
  public void update(Event event, Object data) {}

  @Override
  public void update(Event event, Object... data) {}
}
