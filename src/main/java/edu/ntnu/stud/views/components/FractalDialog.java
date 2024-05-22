package edu.ntnu.stud.views.components;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observable;
import edu.ntnu.stud.utils.Observer;
import edu.ntnu.stud.views.components.NumberField.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * This class represents a dialog box for editing or creating fractals. It extends the Dialog class
 * from the JavaFX library and implements the Observable interface.
 */
public class FractalDialog extends Dialog<String> implements Observable {

  private final List<Observer> observers = new ArrayList<>();
  private final ComboBox<String> typeComboBox = new ComboBox<>();
  private final NumberField xMin;
  private final NumberField yMin;
  private final NumberField xMax;
  private final NumberField yMax;
  private final List<List<NumberField>> transforms = new ArrayList<>();
  private final List<NumberField> transformWeightFields = new ArrayList<>();
  private final GridPane transformsGrid = new GridPane();

  /**
   * Constructor for the FractalDialog class. Initializes the dialog box with the specified
   * transform type, min and max coordinates, and transform values.
   *
   * @param transformType the type of transform
   * @param xMinValue the minimum x-coordinate
   * @param yMinValue the minimum y-coordinate
   * @param xMaxValue the maximum x-coordinate
   * @param yMaxValue the maximum y-coordinate
   * @param transformValues the values of the transforms
   */
  public FractalDialog(
      String transformType,
      double xMinValue,
      double yMinValue,
      double xMaxValue,
      double yMaxValue,
      List<List<Double>> transformValues) {
    setTitle("Fractal");
    setHeaderText("Edit/create a fractal");

    GridPane mainGrid = new GridPane();

    this.typeComboBox.getItems().addAll("Affine", "Julia");
    this.typeComboBox.setValue(transformType);
    this.typeComboBox.setOnAction(
        e -> {
          transformsGrid.getChildren().clear();
          transforms.clear();
        });
    mainGrid.add(new Label("Type:"), 0, 0);
    mainGrid.add(this.typeComboBox, 1, 0);

    xMin = new Builder("x").value(xMinValue).build();
    yMin = new Builder("y").value(yMinValue).build();
    xMax = new Builder("x").value(xMaxValue).build();
    yMax = new Builder("y").value(yMaxValue).build();

    mainGrid.add(new Label("Min Coords:"), 0, 1);
    mainGrid.add(xMin, 1, 1);
    mainGrid.add(yMin, 2, 1);

    mainGrid.add(new Label("Max Coords:"), 0, 2);
    mainGrid.add(xMax, 1, 2);
    mainGrid.add(yMax, 2, 2);

    mainGrid.add(transformsGrid, 0, 3, 3, 1);

    for (List<Double> transform : transformValues) {
      GridPane transformGrid = null;
      if (transformType.equals("Affine")) {
        transformGrid = createAffineTransformUI(transform);
      } else if (transformType.equals("Julia")) {
        transformGrid = createJuliaTransformUI(transform);
      }
      transformsGrid.add(transformGrid, 0, transformsGrid.getRowCount());
    }

    ScrollPane scrollableBox = getScrollPane(mainGrid);
    getDialogPane().setContent(scrollableBox);

    ButtonType buttonTypeOk = new ButtonType("OK", ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);

    setResultConverter(
        dialogButton -> {
          if (dialogButton == buttonTypeOk) {
            String selectedType = typeComboBox.getValue();
            double minX = xMin.getValue();
            double minY = yMin.getValue();
            double maxX = xMax.getValue();
            double maxY = yMax.getValue();
            List<List<Double>> transformValuesResult = new ArrayList<>();
            for (List<NumberField> transformFields : transforms) {
              List<Double> transformValuesRow = new ArrayList<>();
              for (NumberField field : transformFields) {
                transformValuesRow.add(field.getValue());
              }
              transformValuesResult.add(transformValuesRow);
            }
            List<Double> transformWeights = new ArrayList<>();
            for (NumberField field : transformWeightFields) {
              transformWeights.add(field.getValue());
            }

            notifyObservers(
                Event.UPDATE_DESCRIPTION,
                selectedType,
                minX,
                minY,
                maxX,
                maxY,
                transformValuesResult,
                transformWeights);
          }
          return null;
        });
  }

  /**
   * Default constructor for the FractalDialog class. Initializes the dialog box with default
   * values.
   */
  public FractalDialog() {
    this("Affine", 0, 0, 0, 0, new ArrayList<>());
  }

  /**
   * Returns a scrollable box containing the main grid and an "Add Transform" button.
   *
   * @param mainGrid
   * @return a scrollable box containing the main grid and an "Add Transform" button
   */
  private ScrollPane getScrollPane(GridPane mainGrid) {
    Button addTransformButton = new Button("Add Transform");
    addTransformButton.setOnAction(
        e -> {
          GridPane transformGrid = null;
          if (this.typeComboBox.getValue().equals("Affine")) {
            transformGrid = createAffineTransformUI(null);
          } else if (this.typeComboBox.getValue().equals("Julia")) {
            transformGrid = createJuliaTransformUI(null);
          }
          transformsGrid.add(transformGrid, 0, transformsGrid.getRowCount());
        });

    VBox vbox = new VBox(mainGrid, addTransformButton);
    ScrollPane scrollableBox = new ScrollPane(vbox);
    scrollableBox.setFitToWidth(true);
    scrollableBox.setPrefHeight(400);
    return scrollableBox;
  }

  /**
   * Creates the user interface for an affine transform.
   *
   * @param values the values of the transform
   * @return a GridPane representing the user interface for the transform
   */
  private GridPane createAffineTransformUI(List<Double> values) {
    GridPane grid = new GridPane();

    NumberField matrixValue1 =
        new Builder("a00").prefWidth(60).value(values != null ? values.get(0) : 0).build();
    NumberField matrixValue2 =
        new Builder("a01").prefWidth(60).value(values != null ? values.get(1) : 0).build();
    NumberField matrixValue3 =
        new Builder("a10").prefWidth(60).value(values != null ? values.get(2) : 0).build();
    NumberField matrixValue4 =
        new Builder("a11").prefWidth(60).value(values != null ? values.get(3) : 0).build();
    NumberField vectorValue1 =
        new Builder("x").prefWidth(60).value(values != null ? values.get(4) : 0).build();
    NumberField vectorValue2 =
        new Builder("y").prefWidth(60).value(values != null ? values.get(5) : 0).build();

    List<NumberField> transformFields =
        new ArrayList<>(
            Arrays.asList(
                matrixValue1,
                matrixValue2,
                matrixValue3,
                matrixValue4,
                vectorValue1,
                vectorValue2));
    transforms.add(transformFields);

    grid.add(new Label("Matrix Row 1:"), 0, 0);
    grid.add(matrixValue1, 1, 0);
    grid.add(matrixValue2, 2, 0);

    grid.add(new Label("Matrix Row 2:"), 0, 1);
    grid.add(matrixValue3, 1, 1);
    grid.add(matrixValue4, 2, 1);

    grid.add(new Label("Vector:"), 0, 2);
    grid.add(vectorValue1, 1, 2);
    grid.add(vectorValue2, 2, 2);

    NumberField weightField = new Builder("Weight").prefWidth(60).value(0).build();
    transformWeightFields.add(weightField);

    grid.add(new Label("Optional weight:"), 0, 3);
    grid.add(weightField, 1, 3);
    GridPane.setColumnSpan(weightField, 2);

    return grid;
  }

  /**
   * Creates the user interface for a Julia transform.
   *
   * @param values the values of the transform
   * @return a GridPane representing the user interface for the transform
   */
  private GridPane createJuliaTransformUI(List<Double> values) {
    GridPane grid = new GridPane();

    NumberField realPart =
        new Builder("Real").prefWidth(60).value(values != null ? values.get(0) : 0).build();
    NumberField imaginaryPart =
        new Builder("Imaginary").prefWidth(60).value(values != null ? values.get(1) : 0).build();

    List<NumberField> transformFields = new ArrayList<>(Arrays.asList(realPart, imaginaryPart));
    transforms.add(transformFields);

    grid.add(new Label("Complex:"), 0, 0);
    grid.add(realPart, 1, 0);
    grid.add(imaginaryPart, 2, 0);

    NumberField weightField = new Builder("Weight").prefWidth(60).value(0).build();
    transformWeightFields.add(weightField);

    grid.add(new Label("Optional weight:"), 0, 1);
    grid.add(weightField, 1, 1);
    GridPane.setColumnSpan(weightField, 2);

    return grid;
  }

  /**
   * Adds an observer to the list of observers.
   *
   * @param observer the observer to be added
   */
  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Removes an observer from the list of observers.
   *
   * @param observer the observer to be removed
   */
  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers of an event.
   *
   * @param event the event to be notified
   */
  @Override
  public void notifyObservers(Event event) {
    observers.forEach(observer -> observer.update(event));
  }

  /**
   * Notifies all observers of an event and a single data object.
   *
   * @param event the event to be notified
   * @param data the data to be sent with the event
   */
  @Override
  public void notifyObservers(Event event, Object data) {
    observers.forEach(observer -> observer.update(event, data));
  }

  /**
   * Notifies all observers of an event and multiple data objects.
   *
   * @param event the event to be notified
   * @param data the data to be sent with the event
   */
  @Override
  public void notifyObservers(Event event, Object... data) {
    observers.forEach(observer -> observer.update(event, data));
  }
}
