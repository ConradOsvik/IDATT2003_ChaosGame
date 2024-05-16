package edu.ntnu.stud.models;

import java.util.List;

/**
 * This class represents the description of a Chaos Game. It includes a list of transformations and
 * the minimum and maximum coordinates for the game.
 */
public class ChaosGameDescription {
  private final List<Transform2D> transforms;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;

  /**
   * Constructs a new ChaosGameDescription with the given transformations and coordinates.
   *
   * @param transforms the list of transformations for the Chaos Game
   * @param minCoords the minimum coordinates for the Chaos Game
   * @param maxCoords the maximum coordinates for the Chaos Game
   */
  public ChaosGameDescription(
      List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) {
    if (transforms == null || minCoords == null || maxCoords == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new IllegalArgumentException(
          "Minimum coordinates must be less than maximum coordinates");
    }

    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
  }

  /**
   * Returns the list of transformations for the Chaos Game.
   *
   * @return the list of transformations
   */
  public List<Transform2D> getTransforms() {
    return this.transforms;
  }

  /**
   * Returns the minimum coordinates for the Chaos Game.
   *
   * @return the minimum coordinates
   */
  public Vector2D getMinCoords() {
    return minCoords;
  }

  /**
   * Returns the maximum coordinates for the Chaos Game.
   *
   * @return the maximum coordinates
   */
  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ChaosGameDescription description = (ChaosGameDescription) obj;
    return transforms.equals(description.transforms)
        && minCoords.equals(description.minCoords)
        && maxCoords.equals(description.maxCoords);
  }
}
