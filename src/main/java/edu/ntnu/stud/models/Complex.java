package edu.ntnu.stud.models;

public class Complex extends Vector2D {


    public Complex(double realPart, double imaginaryPart) {
       super(realPart, imaginaryPart);
    }

    public double getRealPart() {
        return getX0();
    }

    public double getImaginaryPart() {
        return getX1();
    }

    public double sgn(double value) {
        if (value > 0) {
            return 1;
        } else if (value < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public Complex sqrt(Complex complexNumber) {
        double x = complexNumber.getRealPart();
        double y = complexNumber.getImaginaryPart();

        double realPart = Math.sqrt((Math.sqrt(x * x + y * y) + x) / 2);
        double imaginaryPart = sgn(y) * Math.sqrt((Math.sqrt(x * x + y * y) -x) / 2);

        return new Complex (realPart, imaginaryPart);
    }

}
