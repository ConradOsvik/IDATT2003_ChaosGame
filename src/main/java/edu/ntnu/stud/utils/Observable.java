package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;

/**
 * Interface representing an observable object, or "data" in the model-view-controller design
 * pattern.
 */
public interface Observable {

  /**
   * Adds an observer to the list of observers.
   *
   * @param observer the observer to add
   */
  void addObserver(Observer observer);

  /**
   * Removes an observer from the list of observers.
   *
   * @param observer the observer to remove
   */
  void removeObserver(Observer observer);

  /**
   * Notifies all observers of an event.
   *
   * @param event the event to notify observers of
   */
  void notifyObservers(Event event);

  /**
   * Notifies all observers of an event with data.
   *
   * @param event the event to notify observers of
   * @param data the data to send to observers
   */
  void notifyObservers(Event event, Object data);

  /**
   * Notifies all observers of an event with multiple data objects.
   *
   * @param event the event to notify observers of
   * @param data the data to send to observers
   */
  void notifyObservers(Event event, Object... data);
}
