package edu.ntnu.stud.controllers;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.enums.Route;
import edu.ntnu.stud.utils.Router;
import edu.ntnu.stud.views.RootView;
import javafx.application.Application;
import javafx.stage.Stage;

public class RootController extends Controller {
  private final Application application;
  private final Router router;
  private final RootView view;
  private boolean darkMode = false;

  public RootController(Application application, Stage stage) {
    this.application = application;
    this.view = new RootView(this);
    this.router = new Router(stage, view);

    // Set default stylesheet and scene
    Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
    stage.setScene(view);

    // Add views to router
    GameController gameController = new GameController();
    router.addView(Route.GAME, gameController.getView());
    router.setView(Route.GAME);

    // Show stage
    stage.show();
  }

  @Override
  public void update(Event event) {
    if (event == Event.NEW_GAME) {
      router.setView(Route.GAME);
    }
    
    if (event == Event.LOAD_PREVIOUS_GAME) {
      //LOAD GAME FROM FILE
    }
    if (event == Event.SAVE_GAME) {
      //SAVE GAME
    }
    if (event == Event.DARK_MODE_TOGGLED) {
      this.toggleDarkMode();
    }
    if (event == Event.HELP) {
      //SHOW HELP POPUP
    }
  }

  private void toggleDarkMode() {
    darkMode = !darkMode;

    if (darkMode) {
      Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
      view.getDarkModeButton().setText("Light");
    } else {
      Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
      view.getDarkModeButton().setText("Dark");
    }
  }
}
