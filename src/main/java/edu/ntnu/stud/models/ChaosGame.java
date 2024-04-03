package edu.ntnu.stud.models;

import java.util.Random;

/**
 * This class represents the Chaos Game. It is used to generate fractal images using a set of
 * transformations. The game starts with a point, and repeatedly applies a randomly chosen
 * transformation to that point and plots the result.
 */
public class ChaosGame {
  private final ChaosCanvas canvas;
  private final ChaosGameDescription description;
  private Vector2D currentPoint;
  public final Random random;

  /**
   * Constructs a new ChaosGame with the given description, width, and height.
   *
   * @param description the description of the Chaos Game, including the transformations to use
   * @param width the width of the canvas
   * @param height the height of the canvas
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }

    this.description = description;
    this.canvas =
        new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
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
    for (int i = 0; i < steps; i++) {
      Transform2D transform =
          this.description
              .getTransforms()
              .get(this.random.nextInt(this.description.getTransforms().size()));
      this.currentPoint = transform.transform(this.currentPoint);
      this.canvas.putPixel(this.currentPoint);
    }
  }
}
