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
  @Override
  public Complex add(Vector2D vec) {
    return new Complex(this.getReal() + vec.getX0(), this.getImaginary() + vec.getX1());
  }

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
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Complex complex = (Complex) obj;
    return Double.compare(complex.getReal(), getReal()) == 0 &&
        Double.compare(complex.getImaginary(), getImaginary()) == 0;
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
