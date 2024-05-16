package edu.ntnu.stud.views;

import edu.ntnu.stud.controllers.RootController;
import edu.ntnu.stud.enums.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RootView extends View {
  private final RootController controller;
  private final HBox header;
  private final VBox content;
  private Button darkModeButton;

  public RootView(RootController controller) {
    super(800, 800);
    this.controller = controller;
    this.header = new HBox();
    this.content = new VBox();

    root.getChildren().addAll(header, content);

    addDarkModeButton();

    addObserver(controller);
  }

  public void setChildView(Scene scene) {
    content.getChildren().clear();
    content.getChildren().add(scene.getRoot());
  }

  public void addDarkModeButton() {
    darkModeButton = new Button("Dark");

    darkModeButton.setOnAction(
        event -> {
          notifyObservers(Event.DARK_MODE_TOGGLED);
        });

    header.getChildren().add(darkModeButton);
  }

  public Button getDarkModeButton() {
    return darkModeButton;
  }
}
