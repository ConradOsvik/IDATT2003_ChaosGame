package edu.ntnu.stud.models;

/** Represents a complex number with a real and an imaginary part. Extends the Vector2D class. */
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

  /**
   * Adds the specified vector to this complex point and returns the result as a new Complex.
   *
   * @param vec the vector to be added
   * @return a new Complex that is the result of the addition
   * @throws IllegalArgumentException if the vector is null
   */
  @Override
  public Complex add(Vector2D vec) {
    if (vec == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }

    return new Complex(this.getX0() + vec.getX0(), this.getX1() + vec.getX1());
  }

  /**
   * Subtracts the specified vector from this complex point and returns the result as a new Complex.
   *
   * @param vec the vector to be subtracted
   * @return a new Complex that is the result of the subtraction
   * @throws IllegalArgumentException if the vector is null
   */
  @Override
  public Complex subtract(Vector2D vec) {
    if (vec == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }

    return new Complex(this.getX0() - vec.getX0(), this.getX1() - vec.getX1());
  }

  /**
   * Multiplies this complex point by a scalar and returns the result as a new Complex.
   *
   * @param scalar the scalar to multiply the vector by
   * @return a new Complex that is the result of the multiplication
   */
  @Override
  public Complex multiply(double scalar) {
    return new Complex(this.getX0() * scalar, this.getX1() * scalar);
  }

  /**
   * Returns the square root of the complex number.
   *
   * @return A new Complex object that represents the square root of the complex number.
   */
  public Complex sqrt() {
    double r = Math.hypot(this.getReal(), this.getImaginary());
    double realPart = Math.sqrt((r + this.getReal()) / 2);
    double imaginaryPart = Math.signum(this.getImaginary()) * Math.sqrt((r - this.getReal()) / 2);
    return new Complex(realPart, imaginaryPart);
  }

  /**
   * Checks if the complex number is equal to the specified object. The result is true if and only
   * if the argument is not null and is a Complex object that has the same real and imaginary parts
   * as this Complex object.
   *
   * @param obj The object to compare this Complex against
   * @return true if the given object represents a Complex equivalent to this complex number, false
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
    Complex complex = (Complex) obj;
    return Double.compare(complex.getReal(), getReal()) == 0
        && Double.compare(complex.getImaginary(), getImaginary()) == 0;
  }

  /**
   * Returns a string representation of the complex number.
   *
   * @return A string representation of the complex number in the format "real + imaginary".
   */
  @Override
  public String toString() {
    return this.getReal()
        + (this.getImaginary() < 0 ? " - " : " + ")
        + Math.abs(this.getImaginary())
        + "i";
  }
}
