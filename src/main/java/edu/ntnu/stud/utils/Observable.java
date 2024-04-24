package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;

public interface Observable {
  void addObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers(Event event);
}
