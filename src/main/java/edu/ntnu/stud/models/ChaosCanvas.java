package edu.ntnu.stud.models;

/**
 * This class represents a canvas for the Chaos Game. It is used to store and manipulate pixel data.
 * The canvas is a 2D array of integers, where each integer represents a pixel. The integers can be
 * either 0 or 1, where 0 represents an empty pixel and 1 represents a filled pixel. The class also
 * includes methods for transforming coordinates to indices, getting and setting pixels, and
 * clearing the canvas.
 */
public class ChaosCanvas {
  private final int width;
  private final int height;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private final AffineTransform2D transformCoordsToIndices;
  private int[][] canvas;

  /**
   * Constructs a new ChaosCanvas with the given width, height, minimum and maximum coordinates.
   *
   * @param width the width of the canvas
   * @param height the height of the canvas
   * @param minCoords the minimum coordinates of the canvas
   * @param maxCoords the maximum coordinates of the canvas
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    if (minCoords == null || maxCoords == null) {
      throw new IllegalArgumentException("Coordinates cannot be null");
    }
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new IllegalArgumentException(
          "Minimum coordinates must be less than maximum coordinates");
    }

    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    this.transformCoordsToIndices =
        new AffineTransform2D(
            new Matrix2x2(
                0,
                (double) (this.height - 1) / (this.minCoords.getX1() - this.maxCoords.getX1()),
                (double) (this.width - 1) / (this.maxCoords.getX0() - this.minCoords.getX0()),
                0),
            new Vector2D(
                (this.height - 1)
                    * this.maxCoords.getX1()
                    / (this.maxCoords.getX1() - this.minCoords.getX1()),
                (this.width - 1)
                    * this.minCoords.getX0()
                    / (this.maxCoords.getX0() - this.minCoords.getX0())));
  }

  /**
   * Returns the pixel at the given point.
   *
   * @param point the point to get the pixel from
   * @return the pixel at the given point
   */
  public int getPixel(Vector2D point) {
    Vector2D indices = transformCoordsToIndices.transform(point);
    int i = (int) Math.round(indices.getX0());
    int j = (int) Math.round(indices.getX1());
    return this.canvas[i][j];
  }

  /**
   * Sets the pixel at the given point.
   *
   * @param point the point to set the pixel at
   */
  public void putPixel(Vector2D point) {
    Vector2D indices = this.transformCoordsToIndices.transform(point);
    int i = (int) Math.round(indices.getX0());
    int j = (int) Math.round(indices.getX1());
    if (i >= 0 && i < width && j >= 0 && j < height) {
      this.canvas[i][j] = 1;
    }
  }

  /**
   * Returns the canvas.
   *
   * @return the canvas
   */
  public int[][] getCanvas() {
    return this.canvas;
  }

  /** Clears the canvas by setting all pixels to 0. */
  public void clear() {
    this.canvas = new int[width][height];
  }
}
