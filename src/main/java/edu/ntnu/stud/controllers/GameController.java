package edu.ntnu.stud.controllers;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.views.GameView;

public class GameController extends Controller {
  private final GameView view;

  public GameController() {
    this.view = new GameView(this);
  }

  public GameView getView() {
    return view;
  }
  
  public void update(Event event) {
    if (event == Event.JULIA) {
      //USE JULIA FRACTAL
    }
    if (event == Event.BARNSLEY) {
      //USE BARNSLEY FRACTAL
    }
    if (event == Event.SIERPINSKI) {
      //USE SIERPINSKI FRACTAL
    }
    if (event == Event.CLEAR_CANVAS) {
      view.getChaosCanvas().clear();
    }
    if (event == Event.RUN) {
      view.getChaosCanvas().getCanvas();
    }
  }
}
