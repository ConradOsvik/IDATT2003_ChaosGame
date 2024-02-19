package edu.ntnu.stud.models;

import static org.junit.Assert.assertEquals;

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

  @Test
  public void subtract_subtractionIsCorrect_true(){
    Vector2D vec3 = vec1.subtract(vec2);

    assertEquals(vec1.getX0() - vec2.getX0(), vec3.getX0(), this.precisionDelta);
    assertEquals(vec1.getX1() - vec2.getX1(), vec3.getX1(), this.precisionDelta);
  }
}
