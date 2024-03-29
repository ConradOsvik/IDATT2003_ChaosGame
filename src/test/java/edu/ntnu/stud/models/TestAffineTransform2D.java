package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the AffineTransform2D class. It includes tests
 * for the constructor and the transform method. Each test method in this class is annotated with
 * the @Test annotation. The setup method, annotated with @BeforeEach, is used to initialize the
 * AffineTransform2D object used in the tests.
 */
class TestAffineTransform2D {

  private AffineTransform2D affineTransform2D;

  /**
   * This method is executed before each test. It initializes an AffineTransform2D object with a
   * specific matrix and vector.
   */
  @BeforeEach
  void setup() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = new Vector2D(1, 0);

    this.affineTransform2D = new AffineTransform2D(matrix, vector);
  }

  /** This test checks if the constructor of the AffineTransform2D class works correctly. */
  @Test
  @DisplayName("Test constructor works")
  void constructor_constructsNewAffineTransform2D_works() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = new Vector2D(1, 0);

    AffineTransform2D affineTransform2D = new AffineTransform2D(matrix, vector);
  }

  /**
   * This test checks if the constructor of the AffineTransform2D class throws an
   * IllegalArgumentException when the matrix is null.
   */
  @Test
  @DisplayName("Test constructor with null matrix throws")
  void constructor_constructsNewAffineTransform2DWithNullMatrix_throws() {
    Matrix2x2 matrix = null;
    Vector2D vector = new Vector2D(0, 1);

    assertThrows(IllegalArgumentException.class, () -> new AffineTransform2D(matrix, vector));
  }

  /**
   * This test checks if the constructor of the AffineTransform2D class throws an
   * IllegalArgumentException when the vector is null.
   */
  @Test
  @DisplayName("Test constructor with null vector throws")
  void constructor_constructsNewAffineTransform2DWithNullVector_throws() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = null;

    assertThrows(IllegalArgumentException.class, () -> new AffineTransform2D(matrix, vector));
  }

  /** This test checks if the transform method of the AffineTransform2D class works correctly. */
  @Test
  @DisplayName("Test transform works")
  void transform_transformsPoint_works() {
    Vector2D point = new Vector2D(1, 1);

    Vector2D result = affineTransform2D.transform(point);

    assertEquals(new Vector2D(2, 1), result);
  }

  /**
   * This test checks if the transform method of the AffineTransform2D class throws an
   * IllegalArgumentException when the point is null.
   */
  @Test
  @DisplayName("Test transform with null point throws")
  void transform_transformsPointWithNull_throws() {
    Vector2D point = null;

    assertThrows(IllegalArgumentException.class, () -> affineTransform2D.transform(point));
  }
}
