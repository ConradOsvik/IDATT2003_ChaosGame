package edu.ntnu.stud.views.components;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This class represents a dialog box for loading a file.
 * It extends the Dialog class from the JavaFX library.
 */
public class LoadFileDialog extends Dialog<String> {

  /**
   * Constructor for the LoadFileDialog class.
   * Initializes the dialog box with a title, header text, and a TextField for entering the file path.
   */
  public LoadFileDialog() {
    setTitle("Load file");
    setHeaderText("Choose a file to load");

    ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    Label label = new Label("File path:");
    TextField textField = new TextField();
    textField.setPromptText("File path");

    grid.add(label, 0, 0);
    grid.add(textField, 1, 0);

    getDialogPane().setContent(grid);

    setResultConverter(dialogButton -> {
      if (dialogButton == submitButtonType) {
        return textField.getText();
      }
      return null;
    });
    }
}
