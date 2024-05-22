package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the JuliaTransform class. It includes tests for
 * the constructor and the transform method. Each test method in this class is annotated with
 * the @Test annotation. The setup method, annotated with @BeforeEach, is used to initialize the
 * JuliaTransform object used in the tests. The precisionDelta variable is used to compare double
 * values for equality.
 */
class TestJuliaTransform {

  private JuliaTransform juliaTransform;

  /**
   * This method is executed before each test. It initializes a JuliaTransform object with a
   * specific point and sign.
   */
  @BeforeEach
  void setup() {
    Complex point = new Complex(0.3, 0.6);
    int sign = 1;

    this.juliaTransform = new JuliaTransform(point, sign);
  }

  /** This test checks if the constructor of the JuliaTransform class works correctly. */
  @Test
  @DisplayName("Test constructor works")
  void constructor_constructsNewJuliaTransform_works() {
    Complex point = new Complex(1, 1);
    int sign = 1;

    JuliaTransform juliaTransform = new JuliaTransform(point, sign);
  }

  /**
   * This test checks if the constructor of the JuliaTransform class throws an
   * IllegalArgumentException when the point is null.
   */
  @Test
  @DisplayName("Test constructor with null point throws")
  void constructor_constructsNewJuliaTransformWithNullPoint_throws() {
    Complex point = null;
    int sign = 1;

    assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(point, sign));
  }

  /**
   * This test checks if the constructor of the JuliaTransform class throws an
   * IllegalArgumentException when the sign is invalid.
   */
  @Test
  @DisplayName("Test constructor with invalid sign throws")
  void constructor_constructsNewJuliaTransformWithInvalidSign_throws() {
    Complex point = new Complex(1, 1);
    int sign = 0;

    assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(point, sign));
  }

  /** This test checks if the transform method of the JuliaTransform class works correctly. */
  @Test
  @DisplayName("Test transform works")
  void transform_transformsPoint_works() {
    Vector2D z = new Vector2D(0.4, 0.2);
    Vector2D result = juliaTransform.transform(z);

    double precisionDelta = 0.001;
    assertEquals(0.506, result.getX0(), precisionDelta);
    assertEquals(-0.395, result.getX1(), precisionDelta);
  }

  /**
   * This test checks if the transform method of the JuliaTransform class throws an
   * IllegalArgumentException when the point is null.
   */
  @Test
  @DisplayName("Test transform with null throws")
  void transform_transformsPointWithNull_throws() {
    Vector2D z = null;

    assertThrows(IllegalArgumentException.class, () -> juliaTransform.transform(z));
  }
}
