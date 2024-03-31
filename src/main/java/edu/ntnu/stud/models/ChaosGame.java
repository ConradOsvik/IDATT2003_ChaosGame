package edu.ntnu.stud.models;

import java.util.Random;

public class ChaosGame {
  private final ChaosCanvas canvas;
  private final ChaosGameDescription description;
  private Vector2D currentPoint;
  public final Random random;

  public ChaosGame(ChaosGameDescription description, int width, int height){
    this.description = description;
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
  }

  public ChaosCanvas getCanvas() {
    return this.canvas;
  }

  public void runSteps(int steps){
    for(int i = 0; i < steps; i++){
      Transform2D transform = this.description.getTransforms().get(this.random.nextInt(this.description.getTransforms().size()));
      this.currentPoint = transform.transform(this.currentPoint);
      this.canvas.putPixel(this.currentPoint);
    }
  }
}
