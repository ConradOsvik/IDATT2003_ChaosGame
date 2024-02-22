package edu.ntnu.stud.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestComplex {

    private Complex complex1;
    private Complex complex2;
    private final double precisionDelta = 0.0001;

    @Before
    public void setup(){
        this.complex1 = new Complex(3, 6);
        this.complex2 = new Complex(4, 2);
    }

    @Test
    public void constructor_constructorIsCorrect_true(){
        assertEquals(3, complex1.getRealPart(), this.precisionDelta);
        assertEquals(6, complex1.getImaginaryPart(), this.precisionDelta);
    }

    @Test
    public void getRealPart_realPartIs3_true(){
        assertEquals(3, complex1.getRealPart(), this.precisionDelta);
    }

    @Test
    public void getImaginaryPart_imaginaryPartIs6_true(){
        assertEquals(6, complex1.getImaginaryPart(), this.precisionDelta);
    }

    @Test
    public void sqrt_squareRootIsCorrect_true(){
        assertEquals(new Complex(2.203, 1.362), complex1.sqrt());
    }


}
