package edu.ntnu.stud.models;

/**
 * Represents a 2D vector with two coordinates: x0 and x1.
 * This class provides methods to perform basic vector operations such as addition and subtraction.
 */
public class Vector2D {
  private final double x0;
  private final double x1;

  /**
   * Constructs a new Vector2D with the specified coordinates.
   *
   * @param x0 the first coordinate of the vector
   * @param x1 the second coordinate of the vector
   */
  public Vector2D(double x0, double x1){
    this.x0 = x0;
    this.x1 = x1;
  }

  /**
   * Returns the first coordinate of this vector.
   *
   * @return the first coordinate of this vector
   */
  public double getX0() {
    return x0;
  }

  /**
   * Returns the second coordinate of this vector.
   *
   * @return the second coordinate of this vector
   */
  public double getX1() {
    return x1;
  }

  /**
   * Adds the specified vector to this vector and returns the result as a new Vector2D.
   *
   * @param vec the vector to be added
   * @return a new Vector2D that is the result of the addition
   */
  public Vector2D add(Vector2D vec){
    return new Vector2D(this.getX0() + vec.getX0(), this.getX1() + vec.getX1());
  }

  /**
   * Subtracts the specified vector from this vector and returns the result as a new Vector2D.
   *
   * @param vec the vector to be subtracted
   * @return a new Vector2D that is the result of the subtraction
   */
  public Vector2D subtract(Vector2D vec){
    return new Vector2D(this.getX0() - vec.getX0(), this.getX1() - vec.getX1());
  }
}
