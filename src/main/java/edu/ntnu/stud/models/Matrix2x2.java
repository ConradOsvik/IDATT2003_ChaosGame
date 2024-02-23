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


}
