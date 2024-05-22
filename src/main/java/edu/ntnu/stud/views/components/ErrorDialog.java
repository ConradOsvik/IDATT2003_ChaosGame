package edu.ntnu.stud.views.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is used to display error messages to the user in a dialog box. It uses the Alert class
 * from the JavaFX library to create an error dialog. The dialog box waits for the user to
 * acknowledge the error before it is closed.
 */
public class ErrorDialog {

  /**
   * Displays an error dialog with a specified error message.
   *
   * @param errorMessage the error message to be displayed in the dialog
   */
  public static void showError(String errorMessage) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error occurred");
    alert.setContentText(errorMessage);
    alert.showAndWait();
  }
}
