package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;
/**
 * Interface representing an observer object, or "view" in the model-view-controller design pattern.
 */
public interface Observer {
  void update(Event event);

  void update(Event event, Object data);

  void update(Event event, Object... data);
}
