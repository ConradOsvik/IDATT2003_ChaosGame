package edu.ntnu.stud.models;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the Complex class.
 * It includes tests for the constructor, getReal, getImaginary, and sqrt methods.
 * Each test method in this class is annotated with the @Test annotation.
 * The setup method, annotated with @Before, is used to initialize the Complex object used in the tests.
 * The precisionDelta variable is used to compare double values for equality.
 */
class TestComplex {

    private Complex complex;
    private final double precisionDelta = 0.0001;

    /**
     * This method is executed before each test. It initializes the Complex object used in the tests.
     */
    @BeforeEach
    void setup(){
        this.complex = new Complex(16, 30);
    }

    /**
     * This test checks if the constructor of the Complex class correctly initializes the real and imaginary parts.
     */
    @Test
    void constructor_constructorIsCorrect_true(){
        assertEquals(16, complex.getReal(), this.precisionDelta);
        assertEquals(30, complex.getImaginary(), this.precisionDelta);
    }

    /**
     * This test checks if the getReal method of the Complex class correctly returns the real part.
     */
    @Test
    void getReal_realIs3_true(){
        assertEquals(16, complex.getReal(), this.precisionDelta);
    }

    /**
     * This test checks if the getImaginary method of the Complex class correctly returns the imaginary part.
     */
    @Test
    void getImaginary_imaginaryIs6_true(){
        assertEquals(30, complex.getImaginary(), this.precisionDelta);
    }

    /**
     * This test checks if the sqrt method of the Complex class correctly returns the square root of the complex number.
     */
    @Test
    void sqrt_sqrtIsCorrect_true(){
        Complex complexSqrt = complex.sqrt();

        assertEquals(5, complexSqrt.getReal(), this.precisionDelta);
        assertEquals(3, complexSqrt.getImaginary(), this.precisionDelta);
    }
}
