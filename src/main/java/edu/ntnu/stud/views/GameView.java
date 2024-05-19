package edu.ntnu.stud.views;

import edu.ntnu.stud.controllers.GameController;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.models.ChaosCanvas;
import edu.ntnu.stud.models.Vector2D;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;

public class GameView extends View {
  private final GameController controller;
  private final ChaosCanvas chaosCanvas;
  private final WritableImage fractal;
  
  public GameView(GameController controller) {
    super();
    this.controller = controller;

    chaosCanvas = new ChaosCanvas(800, 600, new Vector2D(0, 0), new Vector2D(800, 800));
    fractal = new WritableImage(800, 800);
    
    BorderPane rootPane = new BorderPane();
    rootPane.setPadding(new Insets(10));
    
    VBox controlPane = new VBox(10);
    controlPane.setPadding(new Insets(10));
    controlPane.setAlignment(Pos.TOP_LEFT);
    
    addFractalMenu(controlPane);
    addClearAndRunButtons(controlPane);
    addStepsControl(controlPane);
    addConstantControl(controlPane);
    addMatrixControl(controlPane);
    addVectorControl(controlPane);
    
    rootPane.setLeft(controlPane);
    rootPane.setCenter(getImageView());
    
    
    
    root.getChildren().add(rootPane);
    
    addObserver(controller);
  }
  public ImageView getImageView() {
    return new ImageView(fractal);
  }
  
  public void addFractalMenu(Pane controlPane) {
    MenuButton fractalMenu = new MenuButton("Fractals");
    MenuItem julia = new MenuItem("Julia");
    MenuItem barnsley = new MenuItem("Barnsley");
    MenuItem sierpinski = new MenuItem("Sierpinski");
    fractalMenu.getItems().addAll(julia, barnsley, sierpinski);
    
    julia.setOnAction(event -> notifyObservers(Event.JULIA));
    barnsley.setOnAction(event -> notifyObservers(Event.BARNSLEY));
    sierpinski.setOnAction(event -> notifyObservers(Event.SIERPINSKI));
    
    Tooltip fractalMenuTooltip = new Tooltip("Use a set fractal");
    fractalMenu.setTooltip(fractalMenuTooltip);
    
    controlPane.getChildren().add(fractalMenu);
  }
  
  public void addClearAndRunButtons(Pane controlPane) {
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER_LEFT);
    
    Button clearButton = new Button("Clear");
    clearButton.setOnAction(event -> notifyObservers(Event.CLEAR_CANVAS));
    Tooltip clearButtonTooltip = new Tooltip("Clear the canvas");
    clearButton.setTooltip(clearButtonTooltip);
    
    Button runButton = new Button("Run");
    runButton.setOnAction(event -> notifyObservers(Event.RUN));
    Tooltip runButtonTooltip = new Tooltip("Run the fractal generation");
    runButton.setTooltip(runButtonTooltip);
    
    buttonBox.getChildren().addAll(clearButton, runButton);
    controlPane.getChildren().add(buttonBox);
  }
  
  public void addStepsControl(Pane controlPane) {
    Label stepsLabel = new Label("Steps:");
    TextField stepsField = new TextField();
    stepsField.setPromptText("Enter number of steps");
    
    stepsField.setOnAction(event -> notifyObservers(Event.STEPS, stepsField.getText()));
    
    Tooltip stepsTooltip = new Tooltip("Set the number of steps (iterations) for the fractal");
    stepsLabel.setTooltip(stepsTooltip);
    stepsField.setTooltip(stepsTooltip);
    
    VBox stepsBox = new VBox(5, stepsLabel, stepsField);
    controlPane.getChildren().add(stepsBox);
  }
  
  public void addConstantControl(Pane controlPane) {
    Label constantLabel = new Label("Constant for a Julia transformation:");
    TextField constantField = new TextField();
    constantField.setPromptText("Enter constant C");
    
    constantField.setOnAction(event -> notifyObservers(Event.CONSTANT, constantField.getText()));
    Tooltip constantTooltip = new Tooltip("Set a constant C for a Julia transformation");
    constantLabel.setTooltip(constantTooltip);
    constantField.setTooltip(constantTooltip);
    
    VBox constantBox = new VBox(5, constantLabel, constantField);
    controlPane.getChildren().add(constantBox);
  }
  
  public void addMatrixControl(Pane controlPane) {
    Label matrixLabel = new Label("Set matrix for an affine transformation:");
    TextField a00 = new TextField();
    a00.setPromptText("Enter a00 value");
    TextField a01 = new TextField();
    a01.setPromptText("Enter a01 value");
    TextField a10 = new TextField();
    a10.setPromptText("Enter a10 value");
    TextField a11 = new TextField();
    a11.setPromptText("Enter a11 value");
    
    a00.setOnAction(event -> notifyObservers(Event.MATRIX00, a00.getText()));
    a01.setOnAction(event -> notifyObservers(Event.MATRIX01, a01.getText()));
    a10.setOnAction(event -> notifyObservers(Event.MATRIX10, a10.getText()));
    a11.setOnAction(event -> notifyObservers(Event.MATRIX11, a11.getText()));
    
    Tooltip matrixTooltip = new Tooltip("Set a matrix for an affine transformation");
    matrixLabel.setTooltip(matrixTooltip);
    a00.setTooltip(matrixTooltip);
    a01.setTooltip(matrixTooltip);
    a10.setTooltip(matrixTooltip);
    a11.setTooltip(matrixTooltip);
    
    GridPane matrixPane = new GridPane();
    matrixPane.setHgap(5);
    matrixPane.setVgap(5);
    matrixPane.add(a00, 0, 0);
    matrixPane.add(a01, 1, 0);
    matrixPane.add(a10, 0, 1);
    matrixPane.add(a11, 1, 1);
    
    VBox matrixBox = new VBox(5, matrixLabel, matrixPane);
    controlPane.getChildren().add(matrixBox);
  }
  
  public void addVectorControl(Pane controlPane) {
    Label vectorLabel = new Label("Set vector for an affine transformation:");
    TextField x0 = new TextField();
    x0.setPromptText("Enter x0 value");
    TextField x1 = new TextField();
    x1.setPromptText("Enter x1 value");
    
    Tooltip vectorTooltip = new Tooltip("Set a vector for an affine transformation");
    vectorLabel.setTooltip(vectorTooltip);
    x0.setTooltip(vectorTooltip);
    x1.setTooltip(vectorTooltip);
    
    HBox vectorPane = new HBox(5, x0, x1);
    
    VBox vectorBox = new VBox(5, vectorLabel, vectorPane);
    controlPane.getChildren().add(vectorBox);
  }
  
  public void displayErrorMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Something went wrong");
    alert.setContentText(message);
    alert.showAndWait();
  }
}