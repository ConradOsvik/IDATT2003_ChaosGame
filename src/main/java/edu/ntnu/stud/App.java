package edu.ntnu.stud;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.controllers.RootController;
import edu.ntnu.stud.views.CLI;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private static CLIController cliController;

  @Override
  public void start(Stage stage) throws IOException {
    RootController rootController = new RootController(this, stage);
  }

  public void initTerminal() {
    CLI cli = new CLI();
    cliController = new CLIController(cli);
  }

  public void startTerminal() {
    cliController.handleRequest();
  }

  public static void launchTerminal() {
    App app = new App();
    app.initTerminal();

    while (true) {
      app.startTerminal();
    }
  }

  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("terminal")) {
      launchTerminal();
    } else {
      launch();
    }
  }
}