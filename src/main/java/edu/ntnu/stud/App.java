package edu.ntnu.stud;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.controllers.ChaosGameController;
import edu.ntnu.stud.views.CLI;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class of the application.
 *
 * <p>The application can take in arguments. By passing in the argument <b>terminal</b>, it will
 * launch the application in terminal mode
 */
public class App extends Application {

  private static CLIController cliController;

  /** Launches the JavaFX application. */
  @Override
  public void start(Stage stage) throws IOException {
    ChaosGameController controller = new ChaosGameController(stage);
  }

  /** Initializes the terminal view and controller */
  public void initTerminal() {
    CLI cli = new CLI();
    cliController = new CLIController(cli);
  }

  /**
   * Starts the terminal by calling the handleRequest method of cliController
   *
   * @see CLIController#handleRequest()
   */
  public void startTerminal() {
    cliController.handleRequest();
  }

  /**
   * Launches the terminal application
   *
   * <p>The application runs in a loop until you quit, making it possible to perform multiple
   * actions
   */
  public static void launchTerminal() {
    App app = new App();
    app.initTerminal();

    while (true) {
      app.startTerminal();
    }
  }

  /**
   * The main method of the application
   *
   * <p>It launches the application in terminal mode if the argument <b>terminal</b> is passed in
   *
   * @param args the arguments passed in. <b>terminal</b> to launch in terminal mode
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("terminal")) {
      launchTerminal();
    } else {
      launch();
    }
  }
}
