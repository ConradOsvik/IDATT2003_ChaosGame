package edu.ntnu.stud.controllers;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.factory.ChaosGameDescriptionFactory;
import edu.ntnu.stud.views.GameView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameController extends Controller {
  private final GameView view;
  private final ChaosGameDescriptionFactory factory;
  
  public GameController() {
    this.view = new GameView(this);
    this.factory = new ChaosGameDescriptionFactory();
  }
  
  public GameView getView() {
    return view;
  }
  
  public void update(Event event) {
    WritableImage fractalImage;
    switch (event) {
      case JULIA:
        System.out.println("Julia event triggered");
        fractalImage = createJuliaFractalImage();
        break;
      case BARNSLEY:
        System.out.println("Barnsley event triggered");
        fractalImage = createBarnsleyFractalImage();
        break;
      case SIERPINSKI:
        System.out.println("Sierpinski event triggered");
        fractalImage = createSierpinskiFractalImage();
        break;
      case CLEAR_CANVAS:
        fractalImage = createClearCanvasImage();
        break;
      default:
        throw new IllegalArgumentException("Unexpected event: " + event);
    }
    view.getImageView().setImage(fractalImage);
    System.out.println("ImageView updated with fractal image.");
  }
  
  private WritableImage createJuliaFractalImage() {
    int width = 800;
    int height = 600;
    WritableImage writableImage = new WritableImage(width, height);
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    // Example drawing code (replace with actual fractal logic)
    gc.setFill(Color.WHITE); // Use javafx.scene.paint.Color
    gc.fillRect(0, 0, width, height);
    gc.setFill(Color.BLACK); // Use javafx.scene.paint.Color
    gc.fillText("Julia Fractal", width / 2, height / 2);
    
    canvas.snapshot(null, writableImage);
    return writableImage;
  }
  
  
  private WritableImage createBarnsleyFractalImage() {
    int width = 800;
    int height = 600;
    WritableImage writableImage = new WritableImage(width, height);
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, width, height);
    gc.setFill(Color.BLACK);
    
    // Barnsley Fern fractal generation
    double x = 0, y = 0;
    Random random = new Random();
    
    System.out.println("Starting Barnsley Fern generation...");
    for (int i = 0; i < 100000; i++) {
      double nextX, nextY;
      double rand = random.nextDouble();
      
      if (rand < 0.01) {
        nextX = 0;
        nextY = 0.16 * y;
      } else if (rand < 0.86) {
        nextX = 0.85 * x + 0.04 * y;
        nextY = -0.04 * x + 0.85 * y + 1.6;
      } else if (rand < 0.93) {
        nextX = 0.2 * x - 0.26 * y;
        nextY = 0.23 * x + 0.22 * y + 1.6;
      } else {
        nextX = -0.15 * x + 0.28 * y;
        nextY = 0.26 * x + 0.24 * y + 0.44;
      }
      
      x = nextX;
      y = nextY;
      
      gc.fillRect(400 + x * 50, 600 - y * 50, 1, 1);  // Scaling and translating the fractal
    }
    
    System.out.println("Barnsley Fern generation completed.");
    canvas.snapshot(null, writableImage);
    
    // Add the canvas to the pane for visual debugging (remove this later if needed)
    view.getRoot().getChildren().add(canvas);
    
    return writableImage;
  }
  
  private WritableImage createSierpinskiFractalImage() {
    int width = 800;
    int height = 600;
    WritableImage writableImage = new WritableImage(width, height);
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    // Example drawing code (replace with actual fractal logic)
    gc.setFill(Color.WHITE); // Use javafx.scene.paint.Color
    gc.fillRect(0, 0, width, height);
    gc.setFill(Color.BLACK); // Use javafx.scene.paint.Color
    gc.fillText("Sierpinski Fractal", width / 2, height / 2);
    
    canvas.snapshot(null, writableImage);
    return writableImage;
  }
  
  private WritableImage createClearCanvasImage() {
    int width = 800;
    int height = 600;
    WritableImage writableImage = new WritableImage(width, height);
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    gc.setFill(Color.WHITE); // Use javafx.scene.paint.Color
    gc.fillRect(0, 0, width, height);
    
    canvas.snapshot(null, writableImage);
    return writableImage;
  }
}
