package edu.ntnu.stud.models;

public class Matrix2x2 {

    private final double a00;
    private final double a01;
    private final double a10;
    private final double a11;

    /**
     * Constructs a new Matrix2x2 with the specified values.
     *
     * @param a00 the first value of the vector
     * @param a01 the second value of the vector
     * @param a10 the third value of the vector
     * @param a11 the fourth value of the vector
     */
    public Matrix2x2(double a00, double a01, double a10, double a11) {
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }

    public double getA00() {
        return a00;
    }

    public double getA01() {
        return a01;
    }

    public double getA10() {
        return a10;
    }

    public double getA11() {
        return a11;
    }

    public Vector2D multiply (Vector2D vec) {
        return new Vector2D(this.a00 * vec.getX0() + this.a01 * vec.getX1(),
                this.a10 * vec.getX0() + this.a11 * vec.getX1());
    }


}
