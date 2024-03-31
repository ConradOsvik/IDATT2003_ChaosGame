package edu.ntnu.stud.models;

public class ChaosCanvas {
  private final int width;
  private final int height;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private final AffineTransform2D transformCoordsToIndices;
  private int[][] canvas;

  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    this.transformCoordsToIndices = new AffineTransform2D(
        new Matrix2x2(0, (double) (this.height - 1) / (this.minCoords.getX1() - this.maxCoords.getX1()),
            (double) (this.width - 1) / (this.maxCoords.getX0() - this.minCoords.getX0()), 0),
        new Vector2D((this.height - 1) * this.maxCoords.getX1() / (this.maxCoords.getX1() - this.minCoords.getX1()),
            (this.width - 1) * this.minCoords.getX0() / (this.maxCoords.getX0() - this.minCoords.getX0()))
    );
  }

  public int getPixel(Vector2D point){
    Vector2D indices = transformCoordsToIndices.transform(point);
    int i = (int) Math.round(indices.getX0());
    int j = (int) Math.round(indices.getX1());
    return this.canvas[i][j];
  }

  public void putPixel(Vector2D point){
    Vector2D indices = this.transformCoordsToIndices.transform(point);
    int i = (int) Math.round(indices.getX0());
    int j = (int) Math.round(indices.getX1());
    if(i >= 0 && i < width && j >= 0 && j < height){
      this.canvas[i][j] = 1;
    }
  }

  public int[][] getCanvas(){
    return this.canvas;
  }

  public void clear(){
    this.canvas = new int[width][height];
  }
}
