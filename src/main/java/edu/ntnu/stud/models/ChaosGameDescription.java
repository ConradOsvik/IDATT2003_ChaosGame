package edu.ntnu.stud.models;

import java.util.List;

public class ChaosGameDescription {
  private final List<Transform2D> transforms;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;

  public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) {
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
  }

  public List<Transform2D> getTransforms(){
    return this.transforms;
  }

  public Vector2D getMinCoords() {
    return minCoords;
  }

  public Vector2D getMaxCoords() {
    return maxCoords;
  }
}
