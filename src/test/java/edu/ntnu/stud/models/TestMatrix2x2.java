package edu.ntnu.stud.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMatrix2x2 {

    private Matrix2x2 matrix;
    private Vector2D vec1;
    private final double precisionDelta = 0.0001;

    @Before
    public void setup(){
        this.matrix = new Matrix2x2(1, 3, 2, 4);
        this.vec1 = new Vector2D(2, 3);
    }

    @Test
    public void constructor_constructorIsCorrect_true(){
        assertEquals(1, matrix.getA00(), this.precisionDelta);
        assertEquals(3, matrix.getA01(), this.precisionDelta);
        assertEquals(2, matrix.getA10(), this.precisionDelta);
        assertEquals(4, matrix.getA11(), this.precisionDelta);
    }

    @Test
    public void getA00_A00is1_true(){
        assertEquals(1, matrix.getA00(), this.precisionDelta);
    }

    @Test
    public void getA01_A01is3_true(){ assertEquals(3, matrix.getA01(), this.precisionDelta);
    }

    @Test
    public void getA10_A10is2_true(){
        assertEquals(2, matrix.getA10(), this.precisionDelta);
    }

    @Test
    public void getA11_A11is4_true(){
        assertEquals(4, matrix.getA11(), this.precisionDelta);
    }

    @Test
    public void multiply_multiplicationIsCorrect_true(){
        Vector2D vec2 = matrix.multiply(vec1);

        assertEquals(matrix.getA00() * vec1.getX0() + matrix.getA01() * vec1.getX1(), vec2.getX0(), this.precisionDelta);
        assertEquals(matrix.getA10() * vec1.getX0() + matrix.getA11() * vec1.getX1(), vec2.getX1(), this.precisionDelta);
    }

}
