package edu.ntnu.stud.models;

/**
 * Represents a complex number with a real and an imaginary part.
 * Extends the Vector2D class.
 */
public class Complex extends Vector2D {

    /**
     * Constructs a new Complex object.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     */
    public Complex(double realPart, double imaginaryPart) {
       super(realPart, imaginaryPart);
    }

    /**
     * Returns the real part of the complex number.
     *
     * @return The real part of the complex number.
     */
    public double getReal() {
        return getX0();
    }

    /**
     * Returns the imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number.
     */
    public double getImaginary() {
        return getX1();
    }

    public double sgn(double value) {
        if (value > 0) {
            return 1;
        } else if (value < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public Complex sqrt() {
        double x = this.getRealPart();
        double y = this.getImaginaryPart();

        double realPart = Math.sqrt((Math.sqrt(x * x + y * y) + x) / 2);
        double imaginaryPart = sgn(y) * Math.sqrt((Math.sqrt(x * x + y * y) -x) / 2);

        return new Complex (realPart, imaginaryPart);
    /**
     * Returns the square root of the complex number.
     *
     * @return A new Complex object that represents the square root of the complex number.
     */
    public Complex sqrt(){
        double r = Math.hypot(this.getReal(), this.getImaginary());
        double realPart = Math.sqrt((r + this.getReal()) / 2);
        double imaginaryPart = Math.signum(this.getImaginary()) * Math.sqrt((r - this.getReal()) / 2);
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Returns a string representation of the complex number.
     *
     * @return A string representation of the complex number in the format "real + imaginary".
     */
    @Override
    public String toString() {
        return this.getReal() + (this.getImaginary() < 0 ? " - " : " + ") + Math.abs(this.getImaginary()) + "i";
    }
}
