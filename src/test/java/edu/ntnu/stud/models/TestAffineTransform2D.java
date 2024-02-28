package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAffineTransform2D {

  private AffineTransform2D affineTransform2D;

  @BeforeEach
  void setup() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = new Vector2D(1, 0);

    this.affineTransform2D = new AffineTransform2D(matrix, vector);
  }

  @Test
  void constructor_constructsNewAffineTransform2D_works() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = new Vector2D(1, 0);

    AffineTransform2D affineTransform2D = new AffineTransform2D(matrix, vector);
  }

  @Test
  void constructor_constructsNewAffineTransform2DWithNullMatrix_throws() {
    Matrix2x2 matrix = null;
    Vector2D vector = new Vector2D(0, 1);

    assertThrows(NullPointerException.class, () -> new AffineTransform2D(matrix, vector));
  }

  @Test
  void constructor_constructsNewAffineTransform2DWithNullVector_throws() {
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = null;

    assertThrows(NullPointerException.class, () -> new AffineTransform2D(matrix, vector));
  }

  @Test
  void transform_transformsPoint_works() {
    Vector2D point = new Vector2D(1, 1);

    Vector2D result = affineTransform2D.transform(point);

    assertEquals(new Vector2D(2, 1), result);
  }

  @Test
  void transform_transformsPointWithNull_throws() {
    Vector2D point = null;

    assertThrows(NullPointerException.class, () -> affineTransform2D.transform(point));
  }
}
