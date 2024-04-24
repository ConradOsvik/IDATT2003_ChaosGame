package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Event;

public interface Observer {
  void update(Event event);
}
