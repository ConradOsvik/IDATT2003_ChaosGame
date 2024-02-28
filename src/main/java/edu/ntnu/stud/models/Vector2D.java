package edu.ntnu.stud.models;

/**
 * Represents a 2D vector with two coordinates: x0 and x1. This class provides methods to perform
 * basic vector operations such as addition and subtraction.
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
  public Vector2D(double x0, double x1) {
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
  public Vector2D add(Vector2D vec) {
    return new Vector2D(this.getX0() + vec.getX0(), this.getX1() + vec.getX1());
  }

  /**
   * Subtracts the specified vector from this vector and returns the result as a new Vector2D.
   *
   * @param vec the vector to be subtracted
   * @return a new Vector2D that is the result of the subtraction
   */
  public Vector2D subtract(Vector2D vec) {
    return new Vector2D(this.getX0() - vec.getX0(), this.getX1() - vec.getX1());
  }

  /**
   * Multiplies this vector by a scalar and returns the result as a new Vector2D.
   *
   * @param scalar the scalar to multiply the vector by
   * @return a new Vector2D that is the result of the multiplication
   */
  public Vector2D multiply(double scalar) {
    return new Vector2D(this.getX0() * scalar, this.getX1() * scalar);
  }

  /**
   * Checks if this vector is equal to the specified object. The result is true if and only if the
   * argument is not null and is a Vector2D object that has the same coordinates as this vector.
   *
   * @param obj The object to compare this Vector2D against
   * @return true if the given object represents a Vector2D equivalent to this vector, false
   * otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Vector2D vector = (Vector2D) obj;
    return Double.compare(vector.getX0(), getX0()) == 0 &&
        Double.compare(vector.getX1(), getX1()) == 0;
  }

  /**
   * Returns a string representation of the vector.
   *
   * @return A string representation of the vector in the format "(x0, x1)".
   */
  @Override
  public String toString() {
    return "(" + getX0() + ", " + getX1() + ")";
  }
}
