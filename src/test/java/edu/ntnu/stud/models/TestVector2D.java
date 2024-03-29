package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the Vector2D class. It includes tests for the
 * constructor, getX0, getX1, add, subtract and multiply methods. Each test method in this class is
 * annotated with the @Test annotation. The setup method, annotated with @Before, is used to
 * initialize the Vector2D objects used in the tests. The precisionDelta variable is used to compare
 * double values for equality.
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

  /** This test checks if the constructor of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test constructor works")
  void constructor_constructorIsCorrect_true() {
    assertEquals(1, vec1.getX0(), this.precisionDelta);
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  /** This test checks if the getX0 method of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test getX0 works")
  void getX0_X0is1_true() {
    assertEquals(1, vec1.getX0(), this.precisionDelta);
  }

  /** This test checks if the getX1 method of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test getX1 works")
  void getX1_X1is0_true() {
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  /** This test checks if the add method of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test add works")
  void add_additionIsCorrect_true() {
    Vector2D vec3 = vec1.add(vec2);

    assertEquals(vec1.getX0() + vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() + vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the add method of the Vector2D class throws an IllegalArgumentException
   * when adding a null vector.
   */
  @Test
  @DisplayName("Test add with null throws")
  void add_additionWithNull_throws() {
    Vector2D vec3 = null;

    assertThrows(IllegalArgumentException.class, () -> vec1.add(vec3));
  }

  /**
   * This test checks if the add method of the Vector2D class correctly handles the addition of
   * vectors with extreme values.
   */
  @Test
  @DisplayName("Test add with extreme values is infinite")
  void add_additionWithExtremeValues_isInfinite() {
    Vector2D vec3 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.add(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }

  /** This test checks if the subtract method of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test subtract works")
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
  @DisplayName("Test subtract with null throws")
  void subtract_subtractionWithNull_throws() {
    Vector2D vec3 = null;

    assertThrows(IllegalArgumentException.class, () -> vec1.subtract(vec3));
  }

  /**
   * This test checks if the subtract method of the Vector2D class correctly handles the subtraction
   * of vectors with extreme values.
   */
  @Test
  @DisplayName("Test subtract with extreme values is infinite")
  void subtract_subtractionWithExtremeValues_isInfinite() {
    Vector2D vec3 = new Vector2D(-Double.MAX_VALUE, -Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.subtract(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }

  /** This test checks if the multiply method of the Vector2D class works correctly. */
  @Test
  @DisplayName("Test multiply works")
  void multiply_multiplicationIsCorrect_true() {
    Vector2D vec3 = vec1.multiply(2);

    assertEquals(vec1.getX0() * 2, vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() * 2, vec3.getX1(), this.precisionDelta);
  }

  /**
   * This test checks if the multiplication method of the Vector2D class correctly handles the
   * multiplication of vectors with extreme values.
   */
  @Test
  @DisplayName("Test multiply with extreme values is infinite")
  void multiply_multiplicationWithExtremeValues_isInfinite() {
    Vector2D vec3 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec4 = vec3.multiply(Double.MAX_VALUE);

    assertTrue(Double.isInfinite(vec4.getX0()));
    assertTrue(Double.isInfinite(vec4.getX1()));
  }
}
