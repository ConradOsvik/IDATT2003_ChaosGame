package edu.ntnu.stud.views;

import edu.ntnu.stud.controllers.ChaosGameController;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.views.components.ErrorDialog;
import edu.ntnu.stud.views.components.FractalDialog;
import edu.ntnu.stud.views.components.LoadFileDialog;
import edu.ntnu.stud.views.components.NumberField;
import edu.ntnu.stud.views.components.SaveFileDialog;
import edu.ntnu.stud.views.components.StyledButton;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChaosGameView extends View {

  private final ChaosGameController chaosGameController;
  private final ImageView imageView = new ImageView();
  private final ComboBox<String> presetComboBox = new ComboBox<>();

  private StyledButton darkModeButton;

  public ChaosGameView(ChaosGameController chaosGameController) {
    super(800, 800);
    this.chaosGameController = chaosGameController;
    addObserver(chaosGameController);

    // Zooming
    imageView.setPreserveRatio(true);
    imageView.setOnScroll(
        event -> {
          double zoomFactor = 1.05;
          double deltaY = event.getDeltaY();

          if (deltaY < 0) {
            zoomFactor = 1 / zoomFactor;
          }

          // calculate the zoom anchor point
          double x = event.getX();
          double y = event.getY();

          // create a zoom transform
          javafx.scene.transform.Affine zoom = new javafx.scene.transform.Affine();
          zoom.appendScale(zoomFactor, zoomFactor, x, y);

          // apply the zoom transform to the image view
          imageView.getTransforms().add(zoom);

          event.consume();
        });

    VBox sidebar = createSidebar();
    VBox chaosGameCanvas = createChaosGameCanvasUI();

    root.getChildren().addAll(chaosGameCanvas, sidebar);
  }

  public void resetZoom() {
    imageView.getTransforms().clear();
  }

  public StyledButton getDarkModeButton() {
    return darkModeButton;
  }

  private VBox createSidebar() {
    VBox sidebar = new VBox(10);
    sidebar.setStyle("-fx-background-color: -color-bg-default");

    HBox mainButtonsContainer = new HBox(10);
    createDarkModeButton();
    StyledButton exitButton = createExitButton();
    mainButtonsContainer.getChildren().addAll(darkModeButton, exitButton);

    VBox presetContainer = createPresetUI();
    HBox fileContainer = createFileUI();
    VBox descriptionButtonsContainer = createDescriptionButtonsUI();
    VBox zoomInfoContainer = createZoomInfoUI();

    sidebar
        .getChildren()
        .addAll(
            mainButtonsContainer,
            presetContainer,
            fileContainer,
            descriptionButtonsContainer,
            zoomInfoContainer);

    return sidebar;
  }

  private void createDarkModeButton() {
    darkModeButton = new StyledButton.Builder("Dark").build();

    darkModeButton.setOnAction(
        event -> {
          notifyObservers(Event.DARK_MODE_TOGGLED);
        });

    Tooltip darkModeTooltip = new Tooltip("Switch between dark and light mode");
    darkModeButton.setTooltip(darkModeTooltip);
  }

  private StyledButton createExitButton() {
    StyledButton exitButton = new StyledButton.Builder("Exit").danger().build();
    exitButton.setOnAction(
        event -> {
          System.exit(0);
        });

    Tooltip exitTooltip = new Tooltip("Exit the game");
    exitButton.setTooltip(exitTooltip);

    return exitButton;
  }

  private VBox createPresetUI() {
    VBox container = new VBox(10);

    Label text = new Label("Preset:");

    presetComboBox
        .getItems()
        .addAll("None", "Sierpinski Triangle", "Barnsley Fern", "Koch Snowflake", "Dragon Curve");
    presetComboBox.setValue("Sierpinski Triangle");
    presetComboBox.setOnAction(
        event -> {
          notifyObservers(Event.SET_PRESET, presetComboBox.getValue());
        });

    container.getChildren().addAll(text, presetComboBox);

    return container;
  }

  public void resetPresetCombobox() {
    presetComboBox.setValue("None");
  }

  private HBox createFileUI() {
    HBox container = new HBox(10);
    container.setAlignment(Pos.CENTER);
    container.setMinHeight(100);
    container.setMaxHeight(100);

    StyledButton openButton = new StyledButton.Builder("Load file").build();
    Tooltip openTooltip = new Tooltip("Press to load a file");
    openButton.setTooltip(openTooltip);
    openButton.setOnAction(
        event -> {
          LoadFileDialog dialog = new LoadFileDialog();
          dialog.showAndWait().ifPresent(filePath -> notifyObservers(Event.LOAD_FILE, filePath));
        });

    StyledButton saveButton = new StyledButton.Builder("Save").build();
    Tooltip saveTooltip = new Tooltip("Press to save the description to a file");
    saveButton.setTooltip(saveTooltip);
    saveButton.setOnAction(
        event -> {
          SaveFileDialog dialog = new SaveFileDialog();
          dialog.showAndWait().ifPresent(fileName -> notifyObservers(Event.SAVE_FILE, fileName));
        });

    container.getChildren().addAll(openButton, saveButton);

    return container;
  }

  private VBox createDescriptionButtonsUI() {
    VBox container = new VBox(10);
    container.setAlignment(Pos.CENTER);
    container.setMinHeight(100);
    container.setMaxHeight(100);

    StyledButton editButton = new StyledButton.Builder("Edit description").prefWidth(180).build();
    editButton.setOnAction(
        event -> {
          notifyObservers(Event.OPEN_EDIT_DESCRIPTION_DIALOG);
        });

    StyledButton createButton =
        new StyledButton.Builder("Create description").prefWidth(180).build();
    createButton.setOnAction(
        event -> {
          this.openDescriptionDialog();
        });

    container.getChildren().addAll(editButton, createButton);

    return container;
  }

  public void openDescriptionDialog(
      String transformType,
      double xMin,
      double yMin,
      double xMax,
      double yMax,
      List<List<Double>> transformValues) {
    FractalDialog dialog =
        new FractalDialog(transformType, xMin, yMin, xMax, yMax, transformValues);
    dialog.addObserver(chaosGameController);
    dialog.showAndWait();
  }

  public void openDescriptionDialog() {
    FractalDialog dialog = new FractalDialog();
    dialog.addObserver(chaosGameController);
    dialog.showAndWait();
  }

  private VBox createZoomInfoUI() {
    VBox container = new VBox(10);
    container.setAlignment(Pos.CENTER);
    container.setMinHeight(100);
    container.setMaxHeight(100);

    Label zoom = new Label("Zoom:");
    Label zoomInInfo = new Label("Zoom in by scrolling\n up on image");
    zoomInInfo.setMinHeight(40);
    Label zoomOutInfo = new Label("Zoom out by scrolling\n down on image");
    zoomOutInfo.setMinHeight(40);

    container.getChildren().addAll(zoom, zoomInInfo, zoomOutInfo);

    return container;
  }

  private VBox createChaosGameCanvasUI() {
    VBox container = new VBox(10);
    container.setStyle("-fx-background-color: #fff");

    HBox controls = createControlsUI();

    container.getChildren().addAll(imageView, controls);

    return container;
  }

  private HBox createControlsUI() {
    HBox controls = new HBox(10);
    controls.setAlignment(Pos.TOP_CENTER);
    controls.setMinHeight(100);
    controls.setMaxHeight(100);

    NumberField iterationsField = new NumberField.Builder("Iterations").prefWidth(160).build();
    StyledButton runButton = new StyledButton.Builder("Run").build();
    runButton.setOnAction(
        event -> {
          if (iterationsField.getText().isEmpty()) {
            return;
          }
          notifyObservers(Event.RUN_CHAOS_GAME, Integer.parseInt(iterationsField.getText()));
        });

    controls.getChildren().addAll(iterationsField, runButton);

    return controls;
  }

  public void setImage(Image image) {
    imageView.setImage(image);
  }

  public void showErrorDialog(String message) {
    ErrorDialog.showError(message);
  }
}
