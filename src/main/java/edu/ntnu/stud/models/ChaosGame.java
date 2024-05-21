package edu.ntnu.stud.models;

import edu.ntnu.stud.enums.Event;
import edu.ntnu.stud.utils.Observable;
import edu.ntnu.stud.utils.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;

/**
 * This class represents the Chaos Game. It is used to generate fractal images using a set of
 * transformations. The game starts with a point, and repeatedly applies a randomly chosen
 * transformation to that point and plots the result.
 */
public class ChaosGame implements Observable {

  private final List<Observer> observers = new ArrayList<>();
  public final Random random = new Random();
  private ChaosGameDescription description;
  private Vector2D currentPoint;
  private ChaosCanvas canvas;
  private int canvasWidth, canvasHeight;

  /**
   * Constructs a new ChaosGame with the given description, width, and height.
   *
   * @param description the description of the Chaos Game, including the transformations to use
   * @param width       the width of the canvas
   * @param height      the height of the canvas
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }

    this.description = description;
    this.canvasWidth = width;
    this.canvasHeight = height;
    this.canvas =
        new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
            description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
  }

  /**
   * Sets the Chaos Game description and resets canvas.
   *
   * @param description the description of the Chaos Game
   */
  public void setChaosGameDescription(ChaosGameDescription description) {
    this.description = description;
    this.currentPoint = new Vector2D(0, 0);
    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());

    notifyObservers(Event.CHAOS_GAME_DESCRIPTION_UPDATED);
  }

  /**
   * Sets the canvas size and resets the canvas.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
   */
  public void setCanvasSize(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }

    this.canvasWidth = width;
    this.canvasHeight = height;

    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());

    notifyObservers(Event.CANVAS_SIZE_UPDATED);
  }

  /**
   * Returns the canvas.
   *
   * @return the canvas
   */
  public ChaosCanvas getCanvas() {
    return this.canvas;
  }

  /**
   * Runs the Chaos Game for a given number of steps.
   *
   * @param steps the number of steps to run the game for
   */
  public void runSteps(int steps) {
    if (steps < 0) {
      throw new IllegalArgumentException("The number of steps cannot be negative");
    }

    if (description.isWeighted()) {
      for (int i = 0; i < steps; i++) {
        double totalWeight = description.getWeightedTransforms().stream()
            .mapToDouble(Pair::getValue)
            .sum();
        double randomWeight = random.nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;
        for (Pair<Transform2D, Double> weightedTransform : description.getWeightedTransforms()) {
          cumulativeWeight += weightedTransform.getValue();
          if (randomWeight <= cumulativeWeight) {
            Transform2D transform = weightedTransform.getKey();
            currentPoint = transform.transform(currentPoint);
            break;
          }
        }
        this.canvas.putPixel(currentPoint);
      }
    } else {
      for (int i = 0; i < steps; i++) {
        Transform2D transform =
            this.description
                .getTransforms()
                .get(this.random.nextInt(this.description.getTransforms().size()));
        this.currentPoint = transform.transform(this.currentPoint);
        this.canvas.putPixel(this.currentPoint);
      }
    }

    notifyObservers(Event.STEPS_RAN);
  }

  @Override
  public void addObserver(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers(Event event) {
    observers.forEach(observer -> observer.update(event));
  }

  @Override
  public void notifyObservers(Event event, Object data) {
    observers.forEach(observer -> observer.update(event, data));
  }

  @Override
  public void notifyObservers(Event event, Object... data) {
    observers.forEach(observer -> observer.update(event, data));
  }
}
