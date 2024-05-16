package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Route;
import edu.ntnu.stud.views.RootView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {
  private final Stage stage;
  private final RootView rootView;
  private final Map<Route, Scene> views;

  public Router(Stage stage, RootView rootView) {
    this.stage = stage;
    this.rootView = rootView;
    this.views = new HashMap<>();
  }

  public void addView(Route route, Scene view) {
    this.views.put(route, view);
  }

  public void removeView(Route route) {
    this.views.remove(route);
  }

  public void setView(Route route) {
    Scene scene = this.views.get(route);
    this.rootView.setChildView(scene);
    stage.setTitle("ChaosGame - " + route.toString());
  }
}
