package edu.ntnu.stud.controllers;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.enums.Route;
import edu.ntnu.stud.utils.Observer;
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
    HomeController homeController = new HomeController();
    router.addView(Route.HOME, homeController.getView());
    router.setView(Route.HOME);

    // Show stage
    stage.show();
  }

  @Override
  public void update(Event event) {
    if(event == Event.DARK_MODE_TOGGLED) {
      this.toggleDarkMode();
    }
  }

  private void toggleDarkMode() {
    darkMode = !darkMode;

    if(darkMode) {
      Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
      view.getDarkModeButton().setText("Light");
    } else {
      Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
      view.getDarkModeButton().setText("Dark");
    }
  }
}
