package edu.ntnu.stud.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestComplex {

    private Complex complex;
    private final double precisionDelta = 0.0001;

    @Before
    public void setup(){
        this.complex = new Complex(16, 30);
    }

    @Test
    public void constructor_constructorIsCorrect_true(){
        assertEquals(16, complex.getReal(), this.precisionDelta);
        assertEquals(30, complex.getImaginary(), this.precisionDelta);
    }

    @Test
    public void getReal_realIs3_true(){
        assertEquals(16, complex.getReal(), this.precisionDelta);
    }

    @Test
    public void getImaginary_imaginaryIs6_true(){
        assertEquals(30, complex.getImaginary(), this.precisionDelta);
    }

    @Test
    public void sqrt_sqrtIsCorrect_true(){
        Complex complexSqrt = complex.sqrt();

        assertEquals(5, complexSqrt.getReal(), this.precisionDelta);
        assertEquals(3, complexSqrt.getImaginary(), this.precisionDelta);
    }
}
