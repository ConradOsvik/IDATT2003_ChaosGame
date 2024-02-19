package edu.ntnu.stud.models;

public class Vector2D {
  private final double x0;
  private final double x1;

  public Vector2D(double x0, double x1){
    this.x0 = x0;
    this.x1 = x1;
  }

  public double getX0() {
    return x0;
  }

  public double getX1() {
    return x1;
  }

  public Vector2D add(Vector2D vec){
    return new Vector2D(this.getX0() + vec.getX0(), this.getX1() + vec.getX1());
  }

  public Vector2D subtract(Vector2D vec){
    return new Vector2D(this.getX0() - vec.getX0(), this.getX1() - vec.getX1());
  }
}
