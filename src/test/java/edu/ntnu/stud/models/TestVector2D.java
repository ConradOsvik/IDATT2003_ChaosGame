package edu.ntnu.stud.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the Vector2D class. It includes tests for the
 * constructor, getX0, getX1, add, and subtract methods. Each test method in this class is annotated
 * with the @Test annotation. The setup method, annotated with @Before, is used to initialize the
 * Vector2D objects used in the tests. The precisionDelta variable is used to compare double values
 * for equality.
 */
class TestVector2D {

  private Vector2D vec1;
  private Vector2D vec2;
  private final double precisionDelta = 0.0001;

  /**
   * This method is executed before each test. It initializes two Vector2D objects with specific
   * coordinates.
   */
  @BeforeEach
  void setup() {
    this.vec1 = new Vector2D(1, 0);
    this.vec2 = new Vector2D(2, 3);
  }

  /**
   * This test checks if the constructor of the Vector2D class works correctly.
   */
  @Test
  void constructor_constructorIsCorrect_true() {
    assertEquals(1, vec1.getX0(), this.precisionDelta);
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the getX0 method of the Vector2D class works correctly.
   */
  @Test
  void getX0_X0is1_true() {
    assertEquals(1, vec1.getX0(), this.precisionDelta);
  }

  /**
   * This test checks if the getX1 method of the Vector2D class works correctly.
   */
  @Test
  void getX1_X1is0_true() {
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the add method of the Vector2D class works correctly.
   */
  @Test
  void add_additionIsCorrect_true() {
    Vector2D vec3 = vec1.add(vec2);

    assertEquals(vec1.getX0() + vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() + vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the add method of the Vector2D class throws a NullPointerException when
   * adding a null vector.
   */
  @Test
  void add_additionWithNull_throws() {
    Vector2D vec3 = null;

    assertThrows(NullPointerException.class, () ->
        vec1.add(vec3)
    );
  }

  /**
   * This test checks if the add method of the Vector2D class correctly handles the addition of
   * vectors with extreme values.
   */
  @Test
  void add_additionWithExtremeValues_isInfinite() {
    Vector2D vec3 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.add(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }

  /**
   * This test checks if the subtract method of the Vector2D class works correctly.
   */
  @Test
  void subtract_subtractionIsCorrect_true() {
    Vector2D vec3 = vec1.subtract(vec2);

    assertEquals(vec1.getX0() - vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() - vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the subtract method of the Vector2D class throws a NullPointerException
   * when subtracting a null vector.
   */
  @Test
  void subtract_subtractionWithNull_throws() {
    Vector2D vec3 = null;

    assertThrows(NullPointerException.class, () ->
        vec1.subtract(vec3)
    );
  }

  /**
   * This test checks if the subtract method of the Vector2D class correctly handles the subtraction
   * of vectors with extreme values.
   */
  @Test
  void subtract_subtractionWithExtremeValues_isInfinite() {
    Vector2D vec3 = new Vector2D(-Double.MAX_VALUE, -Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.subtract(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }
}
