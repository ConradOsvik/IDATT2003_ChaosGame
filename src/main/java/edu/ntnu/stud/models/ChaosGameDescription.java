package edu.ntnu.stud.models;

import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 * This class represents the description of a Chaos Game. It includes a list of transformations and
 * the minimum and maximum coordinates for the game.
 */
public class ChaosGameDescription {
  private final List<Transform2D> transforms;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private final boolean weighted;
  private List<Pair<Transform2D, Double>> weightedTransforms;

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
    this.weighted = false;
  }

  /**
   * Constructs a new ChaosGameDescription with the given weighted transformations and coordinates.
   *
   * @param weightedTransforms the list of weighted transformations for the Chaos Game
   * @param minCoords the minimum coordinates for the Chaos Game
   * @param maxCoords the maximum coordinates for the Chaos Game
   * @param weighted whether the transformations are weighted
   */
  public ChaosGameDescription(
      List<Pair<Transform2D, Double>> weightedTransforms,
      Vector2D minCoords,
      Vector2D maxCoords,
      boolean weighted) {
    if (weightedTransforms == null || minCoords == null || maxCoords == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new IllegalArgumentException(
          "Minimum coordinates must be less than maximum coordinates");
    }

    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = weightedTransforms.stream().map(Pair::getKey).collect(Collectors.toList());
    this.weightedTransforms = weightedTransforms;
    this.weighted = weighted;
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
   * Returns whether the transformations are weighted.
   *
   * @return true if the transformations are weighted, false otherwise
   */
  public boolean isWeighted() {
    return this.weighted;
  }

  /**
   * Returns the list of weighted transformations for the Chaos Game.
   *
   * @return the list of weighted transformations
   */
  public List<Pair<Transform2D, Double>> getWeightedTransforms() {
    return weightedTransforms;
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

  /**
   * Checks if this ChaosGameDescription is equal to the specified object. The result is true if and
   * only if the argument is not null and is a ChaosGameDescription object that has the same
   * transformations, minimum coordinates, and maximum coordinates as this ChaosGameDescription.
   *
   * @param obj The object to compare this ChaosGameDescription against
   * @return true if the given object represents a ChaosGameDescription equivalent to this
   *     ChaosGameDescription, false otherwise
   */
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

  /**
   * Returns a string representation of the ChaosGameDescription.
   *
   * @return A string representation of the ChaosGameDescription in the format
   *     "ChaosGameDescription{transforms=[transform1, transform2, ...], minCoords=(x, y),
   *     maxCoords=(x, y)}".
   */
  @Override
  public String toString() {
    return "ChaosGameDescription{"
        + "transforms="
        + transforms
        + ", minCoords="
        + minCoords
        + ", maxCoords="
        + maxCoords
        + '}';
  }
}
