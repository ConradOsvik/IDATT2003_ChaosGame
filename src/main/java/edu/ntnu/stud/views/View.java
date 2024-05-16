package edu.ntnu.stud.views;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observable;
import edu.ntnu.stud.utils.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public abstract class View extends Scene implements Observable {
  protected final List<Observer> observers = new ArrayList<>();
  protected final VBox root = (VBox) getRoot();

  public View() {
    super(new VBox());
  }

  public View(int width, int height) {
    super(new VBox(), width, height);
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(Event event) {
    observers.forEach(observer -> observer.update(event));
  }
}
