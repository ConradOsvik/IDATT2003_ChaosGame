package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This class is used to test the functionality of the Matrix2x2 class. It includes tests for the
 * constructor, and the multiply method. Each test method in this class is annotated with the @Test
 * annotation. The setup method, annotated with @Before, is used to initialize the Matrix2x2 object
 * and Vector2D object used in the tests. The precisionDelta variable is used to compare double
 * values for equality.
 */
class TestMatrix2x2 {

  private Matrix2x2 matrix;
  private Vector2D vec1;
  private final double precisionDelta = 0.0001;

  /**
   * This method is executed before each test. It initializes a Matrix2x2 object with specific
   * values and a Vector2D object with specific coordinates.
   */
  @BeforeEach
  void setup() {
    this.matrix = new Matrix2x2(1, 3, 2, 4);
    this.vec1 = new Vector2D(2, 3);
  }

  /**
   * This test checks if the constructor of the Matrix2x2 class works correctly.
   */
  @Test
  void constructor_constructorIsCorrect_true() {
    assertEquals(1, matrix.a00(), this.precisionDelta);
    assertEquals(3, matrix.a01(), this.precisionDelta);
    assertEquals(2, matrix.a10(), this.precisionDelta);
    assertEquals(4, matrix.a11(), this.precisionDelta);
  }

  /**
   * This test checks if the multiply method of the Matrix2x2 class works correctly.
   */
  @Test
  void multiply_multiplicationIsCorrect_true() {
    Vector2D vec2 = matrix.multiply(vec1);

    assertEquals(matrix.a00() * vec1.getX0() + matrix.a01() * vec1.getX1(), vec2.getX0(),
        this.precisionDelta);
    assertEquals(matrix.a10() * vec1.getX0() + matrix.a11() * vec1.getX1(), vec2.getX1(),
        this.precisionDelta);
  }

  /**
   * This test checks if the multiply method of the Matrix2x2 class throws a NullPointerException
   * when multiplying with a null vector.
   */
  @Test
  void multiply_multiplicationWithNull_throws() {
    assertThrows(NullPointerException.class, () ->
        matrix.multiply(null)
    );
  }

  /**
   * This test checks if the multiply method of the Matrix2x2 class correctly handles the
   * multiplication of a matrix with a vector with extreme values.
   */
  @Test
  void multiply_multiplicationWithExtremeValues_isInfinite() {
    Vector2D vec2 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);
    Vector2D vec3 = matrix.multiply(vec2);

    assertTrue(Double.isInfinite(vec3.getX0()));
    assertTrue(Double.isInfinite(vec3.getX1()));
  }
}
