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
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChaosGameController extends Controller {

  private final String PATH = "src/main/resources/transforms/";
  private final int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 800;
  private final int DEFAULT_CANVAS_WIDTH = 600, DEFAULT_CANVAS_HEIGHT = 700;
  private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
  private final ChaosGameView chaosGameView;
  private final ChaosGame chaosGame;

  private ChaosGameDescription currentChaosGameDescription;
  private boolean darkMode = false;

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

  private void resizeCanvas(int width, int height) {
    if (width < (DEFAULT_WIDTH - DEFAULT_CANVAS_WIDTH) || height < (DEFAULT_HEIGHT
        - DEFAULT_CANVAS_HEIGHT)) {
      return;
    }

    chaosGame.setCanvasSize(width, height);
  }

  private void runIterations(int iterations) {
    chaosGame.runSteps(iterations);
  }

  private void setPreset(String preset) {
    if (preset.equals("Sierpinski Triangle")) {
      this.currentChaosGameDescription = ChaosGameDescriptionFactory.createSierpinskiDescription();
      chaosGame.setChaosGameDescription(currentChaosGameDescription);
    }
    if (preset.equals("Barnsley Fern")) {
      this.currentChaosGameDescription = ChaosGameDescriptionFactory.createBarnsleyDescription();
      chaosGame.setChaosGameDescription(currentChaosGameDescription);
    }
  }

  private void resetPreset() {
    chaosGameView.resetPresetCombobox();
  }

  private void loadFromFile(String fileName) {
    try {
      String path = PATH + fileName + ".txt";
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(path);
      chaosGame.setChaosGameDescription(chaosGameDescription);
    } catch (FileHandlingException e) {
      chaosGameView.showErrorDialog("Error loading file: " + e.getMessage());
    }
  }

  private void saveToFile(String fileName) {
    try {
      String path = PATH + fileName + ".txt";
      chaosGameFileHandler.writeToFile(currentChaosGameDescription, path);
    } catch (FileHandlingException e) {
      chaosGameView.showErrorDialog("Error saving file: " + e.getMessage());
    }
  }

  private void openEditDescriptionDialog() {
    String transformType =
        currentChaosGameDescription.getTransforms().getFirst() instanceof AffineTransform2D
            ? "Affine" : "Julia";

    int xMinValue = (int) currentChaosGameDescription.getMinCoords().getX0();
    int yMinValue = (int) currentChaosGameDescription.getMinCoords().getX1();
    int xMaxValue = (int) currentChaosGameDescription.getMaxCoords().getX0();
    int yMaxValue = (int) currentChaosGameDescription.getMaxCoords().getX1();

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
      if(transform instanceof JuliaTransform juliaTransform){
        values.add(juliaTransform.getC().getReal());
        values.add(juliaTransform.getC().getImaginary());
      }
      transformValues.add(values);
    }

    chaosGameView.openDescriptionDialog(transformType, xMinValue, yMinValue, xMaxValue, yMaxValue,
        transformValues);
  }

  private void updateChaosGameDescription(double xMin, double yMin, double xMax, double yMax, List<List<Double>> transformValues){
    List<Transform2D> transforms = new ArrayList<>();
    for (List<Double> values : transformValues){
      if (values.size() == 6){
        Matrix2x2 matrix = new Matrix2x2(values.get(0), values.get(1), values.get(2), values.get(3));
        Vector2D vector = new Vector2D(values.get(4), values.get(5));

        AffineTransform2D transform = new AffineTransform2D(matrix, vector);

        transforms.add(transform);
      }
      if (values.size() == 2){
        Complex c = new Complex(values.get(0), values.get(1));

        JuliaTransform transform = new JuliaTransform(c, 1);
        transforms.add(transform);
      }
    }
    Vector2D minCoords = new Vector2D(xMin, yMin);
    Vector2D maxCoords = new Vector2D(xMax, yMax);

    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transforms, minCoords, maxCoords);
    chaosGame.setChaosGameDescription(chaosGameDescription);
    this.currentChaosGameDescription = chaosGameDescription;
  }

  private void resetImage() {
    chaosGameView.setImage(createImageFromMatrix(chaosGame.getCanvas().getCanvas()));
  }

  private void resetImageZoom() {
    chaosGameView.resetZoom();
  }

  private Image createImageFromMatrix(int[][] matrix) {
    WritableImage writableImage = new WritableImage(matrix.length, matrix[0].length);
    PixelWriter pixelWriter = writableImage.getPixelWriter();

    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[0].length; y++) {
        if (matrix[x][y] > 0) {
          pixelWriter.setColor(x, y, Color.rgb(Math.max(0, 255 - (matrix[x][y] * 10)), 0,
              Math.min(255, matrix[x][y] * 10)));
        } else {
          pixelWriter.setColor(x, y, javafx.scene.paint.Color.WHITE);
        }
      }
    }

    return writableImage;
  }

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

  @Override
  public void update(Event event, Object data) {
    switch (event) {
      case Event.RUN_CHAOS_GAME:
        runIterations((int) data);
        break;
      case Event.SET_PRESET:
        setPreset((String) data);
        break;
      case LOAD_FILE:
        loadFromFile((String) data);
        resetPreset();
        break;
      case SAVE_FILE:
        saveToFile((String) data);
        resetPreset();
      default:
        break;
    }
  }

  @Override
  public void update(Event event, Object... data){
    switch (event){
      case Event.UPDATE_DESCRIPTION:
        updateChaosGameDescription((double) data[1], (double) data[2], (double) data[3], (double) data[4], (List<List<Double>>) data[5]);
        resetPreset();
        break;
      default:
        break;
    }
  }
}