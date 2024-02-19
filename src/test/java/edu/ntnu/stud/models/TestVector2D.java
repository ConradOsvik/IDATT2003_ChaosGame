package edu.ntnu.stud.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestVector2D {

  private Vector2D vec1;
  private Vector2D vec2;
  private final double precisionDelta = 0.0001;

  @Before
  public void setup(){
    this.vec1 = new Vector2D(1, 0);
    this.vec2 = new Vector2D(2, 3);
  }

  @Test
  public void constructor_constructorIsCorrect_true(){
    assertEquals(1, vec1.getX0(), this.precisionDelta);
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  @Test
  public void getX0_X0is1_true(){
    assertEquals(1, vec1.getX0(), this.precisionDelta);
  }

  @Test
  public void getX1_X1is0_true(){
    assertEquals(0, vec1.getX1(), this.precisionDelta);
  }

  @Test
  public void add_additionIsCorrect_true(){
    Vector2D vec3 = vec1.add(vec2);

    assertEquals(vec1.getX0() + vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() + vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }

  @Test(expected = NullPointerException.class)
  public void add_additionWithNull_throws(){
    Vector2D vec3 = null;

    vec1.add(vec3);
  }

  @Test
  public void add_additionWithExtremeValues_isInfinite(){
    Vector2D vec3 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.add(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }

  @Test
  public void subtract_subtractionIsCorrect_true(){
    Vector2D vec3 = vec1.subtract(vec2);

    assertEquals(vec1.getX0() - vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() - vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }

  @Test(expected = NullPointerException.class)
  public void subtract_subtractionWithNull_throws(){
    Vector2D vec3 = null;

    vec1.add(vec3);
  }

  @Test
  public void subtract_subtractionWithExtremeValues_isInfinite(){
    Vector2D vec3 = new Vector2D(-Double.MAX_VALUE, -Double.MAX_VALUE);
    Vector2D vec4 = new Vector2D(Double.MAX_VALUE, Double.MAX_VALUE);

    Vector2D vec5 = vec3.subtract(vec4);

    assertTrue(Double.isInfinite(vec5.getX0()));
    assertTrue(Double.isInfinite(vec5.getX1()));
  }
}
