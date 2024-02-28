package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJuliaTransform {
  private JuliaTransform juliaTransform;
  private final double precisionDelta = 0.001;

  @BeforeEach
  void setup() {
    Complex point = new Complex(0.3, 0.6);
    int sign = 1;

    this.juliaTransform = new JuliaTransform(point, sign);
  }

  @Test
  void constructor_constructsNewJuliaTransform_works() {
    Complex point = new Complex(1, 1);
    int sign = 1;

    JuliaTransform juliaTransform = new JuliaTransform(point, sign);
  }

  @Test
  void constructor_constructsNewJuliaTransformWithNullPoint_throws() {
    Complex point = null;
    int sign = 1;

    assertThrows(NullPointerException.class, () -> new JuliaTransform(point, sign));
  }

  @Test
  void transform_transformsPoint_works() {
    Vector2D z = new Vector2D(0.4,0.2);
    Vector2D result = juliaTransform.transform(z);

    assertEquals(0.506, result.getX0(), precisionDelta);
    assertEquals(-0.395, result.getX1(), precisionDelta);
  }

  @Test
  void transform_transformsPointWithNull_throws() {
    Vector2D z = null;

    assertThrows(NullPointerException.class, () -> juliaTransform.transform(z));
  }
}
