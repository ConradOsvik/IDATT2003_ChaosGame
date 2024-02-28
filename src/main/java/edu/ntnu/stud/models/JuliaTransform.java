package edu.ntnu.stud.models;

/**
 * This class represents a Julia Transformation implementing the Transform2D interface. It is used
 * to transform a complex point into another Complex point using a specific complex point c and a
 * sign. The transformation is done by subtracting the complex point c from the complex point z,
 * then taking the square root and multiplying by the sign.
 */
public class JuliaTransform implements Transform2D {

  /**
   * The complex point used in the transformation.
   */
  private final Complex c;
  /**
   * The sign used in the transformation.
   */
  private final int sign;

  /**
   * Constructs a new JuliaTransform with the specified complex point and sign.
   *
   * @param c    the complex number to be used in the transformation
   * @param sign the sign to be used in the transformation
   */
  public JuliaTransform(Complex c, int sign) {
    if(c == null) {
      throw new NullPointerException("The complex point c cannot be null");
    }
    if(sign != 1 && sign != -1) {
      throw new IllegalArgumentException("The sign must be either 1 or -1");
    }

    this.c = c;
    this.sign = sign;
  }

  /**
   * Transforms the specified complex point into a new complex point. The transformation is done by
   * subtracting the complex point c  from the z, and then taking the square root and multiplying
   * the result by the sign.
   *
   * @param z the 2D vector to be transformed
   * @return the transformed vector
   */
  public Vector2D transform(Vector2D z) {
    if(z == null) {
      throw new NullPointerException("The complex point z cannot be null");
    }

    Complex zComplex = new Complex(z.getX0(), z.getX1());
    Complex result = zComplex.subtract(c).sqrt();

    if (this.sign < 0) {
      return result.multiply(sign);
    }

    return result;
  }
}