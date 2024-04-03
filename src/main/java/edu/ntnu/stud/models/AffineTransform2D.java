package edu.ntnu.stud.models;

/**
 * This class represents an affine transformation implementing the Transform2D interface. It is used
 * to transform a vector into a new vector, multiplying it by a transformation matrix and adding a
 * translation vector.
 */
public class AffineTransform2D implements Transform2D {

  /** The 2x2 matrix representing the linear transformation. */
  private final Matrix2x2 matrix;

  /** The 2D vector representing the translation. */
  private final Vector2D vector;

  /**
   * Constructs a new AffineTransform2D with the given matrix and vector.
   *
   * @param matrix the 2x2 matrix representing the linear transformation
   * @param vector the 2D vector representing the translation
   * @throws IllegalArgumentException if the matrix or vector is null
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    if (matrix == null) {
      throw new IllegalArgumentException("The matrix cannot be null");
    }
    if (vector == null) {
      throw new IllegalArgumentException("The vector cannot be null");
    }

    this.matrix = matrix;
    this.vector = vector;
  }

  public Matrix2x2 getMatrix() {
    return this.matrix;
  }

  public Vector2D getVector() {
    return this.vector;
  }

  /**
   * Transforms a point represented by a Vector2D according to the affine transformation. The
   * transformation involves multiplying the point with the matrix and then adding the translation
   * vector.
   *
   * @param point the point to be transformed
   * @return the transformed 2D vector
   * @throws IllegalArgumentException if the point is null
   */
  public Vector2D transform(Vector2D point) {
    if (point == null) {
      throw new IllegalArgumentException("The point cannot be null");
    }

    return matrix.multiply(point).add(vector);
  }
}
