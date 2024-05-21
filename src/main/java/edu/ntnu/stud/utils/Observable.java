package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;

/** Interface representing an observable object, or "data" in the model-view-controller design pattern.
 */
public interface Observable {
  void addObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers(Event event);

  void notifyObservers(Event event, Object data);

  void notifyObservers(Event event, Object... data);
}
