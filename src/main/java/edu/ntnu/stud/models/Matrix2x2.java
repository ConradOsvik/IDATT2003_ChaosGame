package edu.ntnu.stud.models;

public record Matrix2x2(double a00, double a01, double a10, double a11) {
    public Vector2D multiply(Vector2D vec) {
        return new Vector2D(this.a00 * vec.getX0() + this.a01 * vec.getX1(),
            this.a10 * vec.getX0() + this.a11 * vec.getX1());
    }


}
