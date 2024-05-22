package edu.ntnu.stud.views;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observable;
import edu.ntnu.stud.utils.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

/**
 * This class represents a View in the Model-View-Controller (MVC) architecture. It extends the
 * Scene class from the JavaFX library and implements the Observable interface. The class includes
 * methods for adding, removing, and notifying observers.
 */
abstract class View extends Scene implements Observable {

  /** The list of observers of the View. */
  protected final List<Observer> observers = new ArrayList<>();

  /** The root HBox of the View. */
  protected final HBox root = (HBox) getRoot();

  /** Constructor for the View class. Initializes the View with a root HBox. */
  public View() {
    super(new HBox());
  }

  /**
   * Constructor for the View class. Initializes the View with a root HBox and the specified width
   * and height.
   *
   * @param width the width of the View
   * @param height the height of the View
   */
  public View(int width, int height) {
    super(new HBox(), width, height);
  }

  /**
   * Adds an observer to the list of observers.
   *
   * @param observer the observer to add
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Removes an observer from the list of observers.
   *
   * @param observer the observer to remove
   */
  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers of an event.
   *
   * @param event the event to notify observers of
   */
  @Override
  public void notifyObservers(Event event) {
    observers.forEach(observer -> observer.update(event));
  }

  /**
   * Notifies all observers of an event with data.
   *
   * @param event the event to notify observers of
   * @param data the data to send to observers
   */
  @Override
  public void notifyObservers(Event event, Object data) {
    observers.forEach(observer -> observer.update(event, data));
  }

  /**
   * Notifies all observers of an event with multiple data objects.
   *
   * @param event the event to notify observers of
   * @param data the data to send to observers
   */
  @Override
  public void notifyObservers(Event event, Object... data) {
    observers.forEach(observer -> observer.update(event, data));
  }
}
