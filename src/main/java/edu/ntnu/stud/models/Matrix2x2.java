package edu.ntnu.stud.models;

/**
 * Represents a 2x2 matrix.
 * This class is a record, which is a special kind of class in Java.
 * Good for representing immutable data classes.
 * The Matrix2x2 record has four fields representing the elements of the matrix.
 */
public record Matrix2x2(double a00, double a01, double a10, double a11) {
    /**
     * Multiplies this matrix with a given vector.
     *
     * @param vec the vector to multiply with
     * @return a new vector that is the result of the multiplication
     */
    public Vector2D multiply(Vector2D vec) {
        return new Vector2D(this.a00 * vec.getX0() + this.a01 * vec.getX1(),
            this.a10 * vec.getX0() + this.a11 * vec.getX1());
    }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Matrix2x2 matrix = (Matrix2x2) obj;
    return Double.compare(matrix.a00, a00) == 0 &&
        Double.compare(matrix.a01, a01) == 0 &&
        Double.compare(matrix.a10, a10) == 0 &&
        Double.compare(matrix.a11, a11) == 0;
  }

  @Override
  public String toString() {
    return "[[" + a00 + ", " + a01 + "], [" + a10 + ", " + a11 + "]]";
  }
}
