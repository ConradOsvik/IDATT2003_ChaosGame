package edu.ntnu.stud.views.components;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SaveFileDialog extends Dialog<String> {
  public SaveFileDialog() {
    setTitle("Save file");
    setHeaderText("Choose a name for the file to save");

    ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    Label label = new Label("File name:");
    TextField textField = new TextField();
    textField.setPromptText("name");

    grid.add(label, 0, 0);
    grid.add(textField, 1, 0);

    getDialogPane().setContent(grid);

    setResultConverter(
        dialogButton -> {
          if (dialogButton == submitButtonType) {
            return textField.getText();
          }
          return null;
        });
  }
}
