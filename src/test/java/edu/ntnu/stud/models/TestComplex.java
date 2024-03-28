package edu.ntnu.stud.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the Complex class. It includes tests for the
 * constructor, getReal, getImaginary, and sqrt methods. Each test method in this class is annotated
 * with the @Test annotation. The setup method, annotated with @Before, is used to initialize the
 * Complex object used in the tests. The precisionDelta variable is used to compare double values
 * for equality.
 */
class TestComplex {

  private Complex complex1;
  private Complex complex2;
  private final double precisionDelta = 0.0001;

  /**
   * This method is executed before each test. It initializes the Complex object used in the tests.
   */
  @BeforeEach
  void setup() {
    this.complex1 = new Complex(16, 30);
    this.complex2 = new Complex(4, 8);
  }

  /**
   * This test checks if the constructor of the Complex class correctly initializes the real and
   * imaginary parts.
   */
  @Test
  @DisplayName("Test constructor works")
  void constructor_constructorIsCorrect_true() {
    assertEquals(16, complex1.getReal(), this.precisionDelta);
    assertEquals(30, complex1.getImaginary(), this.precisionDelta);
  }

  /**
   * This test checks if the getReal method of the Complex class correctly returns the real part.
   */
  @Test
  @DisplayName("Test getReal works")
  void getReal_realIs16_true() {
    assertEquals(16, complex1.getReal(), this.precisionDelta);
  }

  /**
   * This test checks if the getImaginary method of the Complex class correctly returns the
   * imaginary part.
   */
  @Test
  @DisplayName("Test getImaginary works")
  void getImaginary_imaginaryIs30_true() {
    assertEquals(30, complex1.getImaginary(), this.precisionDelta);
  }

  /**
   * This test checks if the add method of the Complex class correctly adds two complex numbers.
   */
  @Test
  @DisplayName("Test add works")
  void add_additionIsCorrect_true() {
    Complex complex3 = complex1.add(complex2);

    assertEquals(20, complex3.getReal(), this.precisionDelta);
    assertEquals(38, complex3.getImaginary(), this.precisionDelta);
  }

  /**
   * This test checks if the add method of the Complex class throws an IllegalArgumentException when
   * adding complex number that is null.
   */
  @Test
  @DisplayName("Test add with null throws")
  void add_additionWithNull_throws() {
    Complex complex3 = null;

    assertThrows(IllegalArgumentException.class, () ->
        complex1.add(complex3)
    );
  }

  /**
   * This test checks if the add method of the Complex class correctly handles the addition of
   * complex numbers with extreme values.
   */
  @Test
  @DisplayName("Test add with extreme values is infinite")
  void add_additionWithExtremeValues_isInfinite() {
    Complex complex3 = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);
    Complex complex4 = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);

    Complex complex5 = complex3.add(complex4);

    assertTrue(Double.isInfinite(complex5.getX0()));
    assertTrue(Double.isInfinite(complex5.getX1()));
  }

  /**
   * This test checks if the subtract method of the Complex class correctly subtracts two complex
   * numbers.
   */
  @Test
  @DisplayName("Test subtract works")
  void subtract_subtractionIsCorrect_true() {
    Complex complex3 = complex1.subtract(complex2);

    assertEquals(12, complex3.getReal(), this.precisionDelta);
    assertEquals(22, complex3.getImaginary(), this.precisionDelta);
  }

  /**
   * This test checks if the subtract method of the Complex class throws an IllegalArgumentException
   * when subtracting a null complex number.
   */
  @Test
  @DisplayName("Test subtract with null throws")
  void subtract_subtractionWithNull_throws() {
    Complex complex3 = null;

    assertThrows(IllegalArgumentException.class, () ->
        complex1.subtract(complex3)
    );
  }

  /**
   * This test checks if the subtract method of the Complex class correctly handles the subtraction
   * of complex numbers with extreme values.
   */
  @Test
  @DisplayName("Test subtract with extreme values is infinite")
  void subtract_subtractionWithExtremeValues_isInfinite() {
    Complex complex3 = new Complex(-Double.MAX_VALUE, -Double.MAX_VALUE);
    Complex complex4 = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);

    Complex complex5 = complex3.subtract(complex4);

    assertTrue(Double.isInfinite(complex5.getX0()));
    assertTrue(Double.isInfinite(complex5.getX1()));
  }

  /**
   * This test checks if the multiply method of the Complex class correctly multiplies two complex
   * numbers.
   */
  @Test
  @DisplayName("Test multiply works")
  void multiply_multiplicationIsCorrect_true() {
    Complex complex3 = complex1.multiply(2);

    assertEquals(32, complex3.getReal(), this.precisionDelta);
  }

  /**
   * This test checks if the multiply method of the Complex class correctly handles the
   * multiplication of complex numbers with extreme values.
   */
  @Test
  @DisplayName("Test multiply with extreme values is infinite")
  void multiply_multiplicationWithExtremeValues_isInfinite() {
    Complex complex3 = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);

    Complex complex4 = complex3.multiply(Double.MAX_VALUE);

    assertTrue(Double.isInfinite(complex4.getX0()));
    assertTrue(Double.isInfinite(complex4.getX1()));
  }

  /**
   * This test checks if the sqrt method of the Complex class correctly returns the square root of
   * the complex number.
   */
  @Test
  void sqrt_sqrtIsCorrect_true() {
    Complex complexSqrt = complex1.sqrt();

    assertEquals(5, complexSqrt.getReal(), this.precisionDelta);
    assertEquals(3, complexSqrt.getImaginary(), this.precisionDelta);
  }
}
