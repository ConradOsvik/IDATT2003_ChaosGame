package edu.ntnu.stud.views.home;

import edu.ntnu.stud.controllers.HomeController;
import edu.ntnu.stud.views.View;
import javafx.scene.text.Text;

public class HomeView extends View {
  private final HomeController controller;

  public HomeView(HomeController controller) {
    super();
    this.controller = controller;
    root.getChildren().add(new Text("Home"));

    addObserver(controller);
  }
}
