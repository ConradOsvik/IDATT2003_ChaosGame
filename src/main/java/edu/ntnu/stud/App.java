package edu.ntnu.stud;

import edu.ntnu.stud.controllers.CLIController;
import edu.ntnu.stud.views.CLI;

public class App {
  private static CLIController cliController;

  public static void init() {
    CLI cli = new CLI();
    cliController = new CLIController(cli);
  }

  public static void start() {
    cliController.handleRequest();
  }

  public static void main(String[] args) {
    init();

    while (true) {
      start();
    }
  }
}
