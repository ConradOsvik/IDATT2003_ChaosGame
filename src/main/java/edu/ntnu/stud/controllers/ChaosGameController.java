package edu.ntnu.stud.controllers;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.exceptions.FileHandlingException;
import edu.ntnu.stud.factory.ChaosGameDescriptionFactory;
import edu.ntnu.stud.models.AffineTransform2D;
import edu.ntnu.stud.models.ChaosGame;
import edu.ntnu.stud.models.ChaosGameDescription;
import edu.ntnu.stud.models.ChaosGameFileHandler;
import edu.ntnu.stud.models.Complex;
import edu.ntnu.stud.models.JuliaTransform;
import edu.ntnu.stud.models.Matrix2x2;
import edu.ntnu.stud.models.Transform2D;
import edu.ntnu.stud.models.Vector2D;
import edu.ntnu.stud.views.ChaosGameView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * This class is the controller for the Chaos Game application.
 * It extends the Controller interface and is responsible for handling user input and
 * updating the view and model accordingly.
 */
public class ChaosGameController extends Controller {

  private final String PATH = "src/main/resources/transforms/";
  private final int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 800;
  private final int DEFAULT_CANVAS_WIDTH = 600, DEFAULT_CANVAS_HEIGHT = 700;
  private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
  private final ChaosGameView chaosGameView;
  private final ChaosGame chaosGame;
  private ChaosGameDescription currentChaosGameDescription;
  private boolean darkMode = false;

  /**
   * Constructs a new ChaosGameController with the given stage.
   *
   * @param stage the stage to display the application in
   */
  public ChaosGameController(Stage stage) {
    super();
    this.chaosGameView = new ChaosGameView(this);

    this.currentChaosGameDescription = ChaosGameDescriptionFactory.createSierpinskiDescription();
    this.chaosGame = new ChaosGame(currentChaosGameDescription, DEFAULT_CANVAS_WIDTH,
        DEFAULT_CANVAS_HEIGHT);
    chaosGame.addObserver(this);

    this.resetImage();

    setup(stage);
  }

  /**
   * Sets up the Chaos Game application with the given stage.
   * Adds listeners to the stage width and height properties to resize the canvas accordingly.
   *
   * @param stage the stage to display the application in
   */
  private void setup(Stage stage) {
    Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

    stage.widthProperty().addListener((observable, oldValue, newValue) -> {
      resizeCanvas(newValue.intValue() - (DEFAULT_WIDTH - DEFAULT_CANVAS_WIDTH),
          (int) stage.getHeight() - (DEFAULT_HEIGHT - DEFAULT_CANVAS_HEIGHT));
    });

    stage.heightProperty().addListener((observable, oldValue, newValue) -> {
      resizeCanvas((int) stage.getWidth() - (DEFAULT_WIDTH - DEFAULT_CANVAS_WIDTH),
          newValue.intValue() - (DEFAULT_HEIGHT - DEFAULT_CANVAS_HEIGHT));
    });

    stage.setScene(chaosGameView);
    stage.setTitle("Chaos Game");
    stage.show();
  }

  /**
   * Resizes the canvas to the given width and height.
   * The canvas is only resized if the new width and height are greater than the default width and
   * height minus the default canvas width and height.
   *
   * @param width the new width of the canvas
   * @param height the new height of the canvas
   */
  private void resizeCanvas(int width, int height) {
    if (width < (DEFAULT_WIDTH - DEFAULT_CANVAS_WIDTH) || height < (DEFAULT_HEIGHT
        - DEFAULT_CANVAS_HEIGHT)) {
      return;
    }

    chaosGame.setCanvasSize(width, height);
  }

  /**
   * Runs the Chaos Game for the given number of iterations.
   *
   * @param iterations the number of iterations to run
   */
  private void runIterations(int iterations) {
    chaosGame.runSteps(iterations);
  }

  /**
   * Sets the description of the Chaos Game to the given preset.
   *
   * @param preset the preset to set the description to
   */
  private void setDescriptionWithPreset(String preset) {
    ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.createDescription(
        preset);
    if (chaosGameDescription != null) {
      this.currentChaosGameDescription = chaosGameDescription;
      chaosGame.setChaosGameDescription(currentChaosGameDescription);
    }
  }

  /**
   * Resets the preset combobox in the view.
   */
  private void resetPreset() {
    chaosGameView.resetPresetCombobox();
  }

  /**
   * Loads the Chaos Game description from the given file.
   *
   * @param fileName the name of the file to load the description from
   */
  private void loadFromFile(String fileName) {
    try {
      String path = PATH + fileName + ".txt";
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(path);
      chaosGame.setChaosGameDescription(chaosGameDescription);
      currentChaosGameDescription = chaosGameDescription;
    } catch (FileHandlingException e) {
      chaosGameView.showErrorDialog("Error loading file: " + e.getMessage());
    }
  }

  /**
   * Saves the current Chaos Game description to the given file.
   *
   * @param fileName the name of the file to save the description to
   */
  private void saveToFile(String fileName) {
    try {
      String path = PATH + fileName + ".txt";
      chaosGameFileHandler.writeToFile(currentChaosGameDescription, path);
    } catch (FileHandlingException e) {
      chaosGameView.showErrorDialog("Error saving file: " + e.getMessage());
    }
  }

  /**
   * Opens the edit description dialog in the view.
   */
  private void openEditDescriptionDialog() {
    String transformType =
        currentChaosGameDescription.getTransforms().getFirst() instanceof AffineTransform2D
            ? "Affine" : "Julia";

    double xMinValue = (double) currentChaosGameDescription.getMinCoords().getX0();
    double yMinValue = (double) currentChaosGameDescription.getMinCoords().getX1();
    double xMaxValue = (double) currentChaosGameDescription.getMaxCoords().getX0();
    double yMaxValue = (double) currentChaosGameDescription.getMaxCoords().getX1();

    List<List<Double>> transformValues = new ArrayList<>();
    for (Transform2D transform : currentChaosGameDescription.getTransforms()) {
      List<Double> values = new ArrayList<>();
      if (transform instanceof AffineTransform2D affineTransform) {
        values.add(affineTransform.getMatrix().a00());
        values.add(affineTransform.getMatrix().a01());
        values.add(affineTransform.getMatrix().a10());
        values.add(affineTransform.getMatrix().a11());
        values.add(affineTransform.getVector().getX0());
        values.add(affineTransform.getVector().getX1());
      }
      if (transform instanceof JuliaTransform juliaTransform) {
        values.add(juliaTransform.getC().getReal());
        values.add(juliaTransform.getC().getImaginary());
      }
      transformValues.add(values);
    }

    chaosGameView.openDescriptionDialog(transformType, xMinValue, yMinValue, xMaxValue, yMaxValue,
        transformValues);
  }

  /**
   * Updates the Chaos Game description with the given values for .
   *
   * @param xMin the minimum x-coordinate of the canvas
   * @param yMin the minimum y-coordinate of the canvas
   * @param xMax the maximum x-coordinate of the canvas
   * @param yMax the maximum y-coordinate of the canvas
   * @param transformValues the values of the transforms
   * @param transformWeights the weights of the transforms
   */
  private void updateChaosGameDescription(double xMin, double yMin, double xMax, double yMax,
      List<List<Double>> transformValues, List<Double> transformWeights) {
    try {
      boolean weighted = transformWeights.stream().allMatch(weight -> weight > 0);

      List<Transform2D> transforms = new ArrayList<>();
      for (List<Double> values : transformValues) {
        if (values.size() == 6) {
          Matrix2x2 matrix = new Matrix2x2(values.get(0), values.get(1), values.get(2),
              values.get(3));
          Vector2D vector = new Vector2D(values.get(4), values.get(5));

          AffineTransform2D transform = new AffineTransform2D(matrix, vector);

          transforms.add(transform);
        }
        if (values.size() == 2) {
          Complex c = new Complex(values.get(0), values.get(1));

          JuliaTransform transform1 = new JuliaTransform(c, 1);
          JuliaTransform transform2 = new JuliaTransform(c, -1);
          transforms.add(transform1);
          transforms.add(transform2);
        }
      }
      Vector2D minCoords = new Vector2D(xMin, yMin);
      Vector2D maxCoords = new Vector2D(xMax, yMax);

      if (weighted) {
        List<Pair<Transform2D, Double>> weightedTransforms = IntStream.range(0, transforms.size())
            .mapToObj(i -> new Pair<>(transforms.get(i), transformWeights.get(i)))
            .toList();

        this.currentChaosGameDescription = new ChaosGameDescription(weightedTransforms,
            minCoords, maxCoords,
            true);
        chaosGame.setChaosGameDescription(currentChaosGameDescription);
      } else {
        this.currentChaosGameDescription = new ChaosGameDescription(transforms, minCoords,
            maxCoords);
        chaosGame.setChaosGameDescription(currentChaosGameDescription);
      }


    } catch (Exception e) {
      chaosGameView.showErrorDialog("Error updating description: " + e.getMessage());
    }
  }

  /**
   * Resets the image of the Chaos Game.
   */
  private void resetImage() {
    chaosGameView.setImage(createImageFromMatrix(chaosGame.getCanvas().getCanvas()));
  }

  /**
   * Resets the zoom of the image.
   */
  private void resetImageZoom() {
    chaosGameView.resetZoom();
  }

  /**
   * Creates an image from the given matrix.
   *
   * @param matrix the matrix to create the image from
   * @return the image created from the matrix
   */
  private Image createImageFromMatrix(int[][] matrix) {
    WritableImage writableImage = new WritableImage(matrix.length, matrix[0].length);
    PixelWriter pixelWriter = writableImage.getPixelWriter();

    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[0].length; y++) {
        if (matrix[x][y] > 0) {
          int red = Math.max(0, Math.min(255, 255 - (matrix[x][y] * 10)));
          int blue = Math.max(0, Math.min(255, matrix[x][y] * 10));
          pixelWriter.setColor(x, y, Color.rgb(red, 0, blue));
        } else {
          pixelWriter.setColor(x, y, javafx.scene.paint.Color.WHITE);
        }
      }
    }

    return writableImage;
  }

  /**
   * Toggles the dark mode of the application.
   */
  private void toggleDarkMode() {
    darkMode = !darkMode;

    if (darkMode) {
      Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
      chaosGameView.getDarkModeButton().setText("Light");
    } else {
      Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
      chaosGameView.getDarkModeButton().setText("Dark");
    }
  }

  /**
   * Updates the Chaos Game view when an event occurs
   * The event can be related to dark mode, running steps, updating the canvas size, or
   * opening the description dialogue
   *
   * @param event the event that occurred
   */
  @Override
  public void update(Event event) {
    switch (event) {
      case Event.DARK_MODE_TOGGLED:
        toggleDarkMode();
        break;
      case Event.STEPS_RAN:
        resetImage();
        break;
      case Event.CANVAS_SIZE_UPDATED, Event.CHAOS_GAME_DESCRIPTION_UPDATED:
        resetImage();
        resetImageZoom();
        break;
      case Event.OPEN_EDIT_DESCRIPTION_DIALOG:
        openEditDescriptionDialog();
        break;
      default:
        break;
    }
  }
  /**
   * Updates the Chaos Game view when an event occurs
   * The event can be related to running the chaos game, setting a preset,
   * loading a file, or saving a file
   *
   * @param event the event that occurred
   * @param data the data associated with the event
   */
  @Override
  public void update(Event event, Object data) {
    switch (event) {
      case Event.RUN_CHAOS_GAME:
        runIterations((int) data);
        break;
      case Event.SET_PRESET:
        setDescriptionWithPreset((String) data);
        break;
      case LOAD_FILE:
        loadFromFile((String) data);
        resetPreset();
        break;
      case SAVE_FILE:
        saveToFile((String) data);
      default:
        break;
    }
  }

  /**
   * Updates the Chaos Game view when an event occurs
   * The event is related to updating the description of the chaos game
   *
   * @param event the event that occurred
   * @param data the data associated with the event
   */
  @Override
  public void update(Event event, Object... data) {
    switch (event) {
      case Event.UPDATE_DESCRIPTION:
        updateChaosGameDescription((double) data[1], (double) data[2], (double) data[3],
            (double) data[4], (List<List<Double>>) data[5], (List<Double>) data[6]);
        resetPreset();
        break;
      default:
        break;
    }
  }
}
