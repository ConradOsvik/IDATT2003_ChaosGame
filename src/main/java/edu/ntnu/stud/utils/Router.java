package edu.ntnu.stud.utils;

import edu.ntnu.stud.enums.Route;
import edu.ntnu.stud.views.View;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {
  private final Stage stage;
  private final Map<Route, Scene> views;
  public Router(Stage stage){
    this.stage = stage;
    this.views = new HashMap<>();
  }
  public void addView(Route route, View view){
    this.views.put(route, view);
  }
  public void removeView(Route route){
    this.views.remove(route);
  }
  public void setView(Route route){
    Scene scene = this.views.get(route);
    this.stage.setScene(scene);
    stage.setTitle("ChaosGame - " + route.toString());
  }
}
