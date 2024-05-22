package edu.ntnu.stud.models;

/**
 * Represents a 2x2 matrix. This class is a record, which is a special kind of class in Java. Good
 * for representing immutable data classes. The Matrix2x2 record has four fields representing the
 * elements of the matrix.
 *
 * @param a00 the element in the first row and first column
 * @param a01 the element in the first row and second column
 * @param a10 the element in the second row and first column
 * @param a11 the element in the second row and second column
 */
public record Matrix2x2(double a00, double a01, double a10, double a11) {

  /**
   * Multiplies this matrix with a given vector.
   *
   * @param vec the vector to multiply with
   * @return a new vector that is the result of the multiplication
   * @throws IllegalArgumentException if the vector is null
   */
  public Vector2D multiply(Vector2D vec) {
    if (vec == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }

    return new Vector2D(
        this.a00 * vec.getX0() + this.a01 * vec.getX1(),
        this.a10 * vec.getX0() + this.a11 * vec.getX1());
  }

  /**
   * Checks if this matrix is equal to the specified object. The result is true if and only if the
   * argument is not null and is a Matrix2x2 object that has the same elements as this matrix.
   *
   * @param obj The object to compare this Matrix2x2 against
   * @return true if the given object represents a Matrix2x2 equivalent to this matrix, false
   *     otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Matrix2x2 matrix = (Matrix2x2) obj;
    return Double.compare(matrix.a00, a00) == 0
        && Double.compare(matrix.a01, a01) == 0
        && Double.compare(matrix.a10, a10) == 0
        && Double.compare(matrix.a11, a11) == 0;
  }

  /**
   * Returns a string representation of the matrix.
   *
   * @return A string representation of the matrix in the format "[[a00, a01], [a10, a11]]".
   */
  @Override
  public String toString() {
    return "[[" + a00 + ", " + a01 + "], [" + a10 + ", " + a11 + "]]";
  }
}
