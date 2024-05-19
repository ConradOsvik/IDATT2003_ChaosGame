package edu.ntnu.stud.views;

import edu.ntnu.stud.controllers.RootController;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.views.components.StyledButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RootView extends View {
  private final RootController controller;
  private final HBox header;
  private final VBox content;
  private Button darkModeButton;

  public RootView(RootController controller) {
    super(800, 800);
    this.controller = controller;
    this.header = new HBox();
    this.content = new VBox();

    root.getChildren().addAll(header, content);

    addHeaderButtons();

    addObserver(controller);
  }

  public void setChildView(Scene scene) {
    content.getChildren().clear();
    content.getChildren().add(scene.getRoot());
  }

  public void addHeaderButtons() {
    addNewGameButton();
    addLoadPreviousGameButton();
    addSaveButton();
    
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);
    header.getChildren().add(spacer);
    
    addDarkModeButton();
    addHelpButton();
  }
  
  
  public void addDarkModeButton() {
    darkModeButton = new StyledButton.Builder("Dark").build();

    darkModeButton.setOnAction(
        event -> {
          notifyObservers(Event.DARK_MODE_TOGGLED);
        });
    
    Tooltip darkModeTooltip = new Tooltip("Switch between dark and light mode");
    darkModeButton.setTooltip(darkModeTooltip);
    
    header.getChildren().add(darkModeButton);
  }

  public Button getDarkModeButton() {
    return darkModeButton;
  }
  
  public void addNewGameButton(){
    Button newGameButton = new StyledButton.Builder("New game").build();
    newGameButton.setOnAction(
        event -> {
          notifyObservers(Event.NEW_GAME);
        });
    
    Tooltip newGameTooltip = new Tooltip("Start a new game");
    newGameButton.setTooltip(newGameTooltip);
    
    header.getChildren().add(newGameButton);
  }
  
  public void addLoadPreviousGameButton(){
    Button loadGameButton = new StyledButton.Builder("Load game").build();
    loadGameButton.setOnAction(
        event -> {
          notifyObservers(Event.LOAD_PREVIOUS_GAME);
        });
   
    Tooltip loadGameTooltip = new Tooltip("Load an old game");
    loadGameButton.setTooltip(loadGameTooltip);
   
    header.getChildren().add(loadGameButton);
  }
  
  public void addSaveButton() {
    Button saveButton = new StyledButton.Builder("Save").build();
    
    saveButton.setOnAction(
        event -> {
          notifyObservers(Event.SAVE_GAME);
        });
    
    Tooltip saveButtonTooltip = new Tooltip("Save this fractal");
    saveButton.setTooltip(saveButtonTooltip);
    
    header.getChildren().add(saveButton);
  }
  
  public void addHelpButton(){
    Button homeButton = new StyledButton.Builder("Help").build();
    homeButton.setOnAction(
        event -> {
          notifyObservers(Event.HELP);
        });
    
    header.getChildren().add(homeButton);
  }
}
