package edu.ntnu.stud.controllers;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observer;

/**
 * This class represents a controller in the MVC architecture. It is used to handle user input and
 * update the model and view accordingly.
 */
abstract class Controller implements Observer {

  /** Handles the user request. */
  @Override
  public void update(Event event) {}

  /**
   * Handles the user request with the specified data.
   *
   * @param event the event to be used
   * @param data the data to be used
   */
  @Override
  public void update(Event event, Object data) {}

  /**
   * Handles the user request with the more than one specified data.
   *
   * @param event the event to be used
   * @param data the data to be used
   */
  @Override
  public void update(Event event, Object... data) {}
}
