package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;

/**
 * Interface representing an observer object, or "view" in the model-view-controller design pattern.
 */
public interface Observer {

  /**
   * Updates the observer with the specified event.
   *
   * @param event the event to update the observer with
   */
  void update(Event event);

  /**
   * Updates the observer with the specified event and data.
   *
   * @param event the event to update the observer with
   * @param data the data to update the observer with
   */
  void update(Event event, Object data);

  /**
   * Updates the observer with the specified event and multiple data objects.
   *
   * @param event the event to update the observer with
   * @param data the data to update the observer with
   */
  void update(Event event, Object... data);
}
