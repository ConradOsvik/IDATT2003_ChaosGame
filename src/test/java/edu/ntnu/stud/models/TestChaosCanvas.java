package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the ChaosCanvas class. It tests the functionality of the
 * ChaosCanvas class methods and constructors.Each test method in this class is annotated with
 * the @Test annotation. The setup method, annotated with @BeforeEach, is used to initialize the
 * ChaosCanvas object used in the tests.
 *
 * @see edu.ntnu.stud.models.ChaosCanvas
 */
class TestChaosCanvas {

  private ChaosCanvas chaosCanvas;
  private AffineTransform2D transformCoordsToIndices;

  @BeforeEach
  void setup() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    this.chaosCanvas = new ChaosCanvas(width, height, minCoords, maxCoords);
    this.transformCoordsToIndices =
        new AffineTransform2D(
            new Matrix2x2(
                0,
                (double) (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
                (double) (width - 1) / (maxCoords.getX0() - minCoords.getX0()),
                0),
            new Vector2D(
                (height - 1) * maxCoords.getX1() / (maxCoords.getX1() - minCoords.getX1()),
                (width - 1) * minCoords.getX0() / (maxCoords.getX0() - minCoords.getX0())));
  }

  @Test
  @DisplayName("Test constructor works")
  void constructor_constructsNewChaosCanvas_works() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);

    ChaosCanvas chaosCanvas = new ChaosCanvas(width, height, minCoords, maxCoords);

    assertNotNull(chaosCanvas);
  }

  @Test
  @DisplayName("Test constructor with negative width throws")
  void constructor_constructsWithNegativeWidth_throws() {
    int width = -40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with negative height throws")
  void constructor_constructsWithNegativeHeight_throws() {
    int width = 40, height = -40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with null minCoords throws")
  void constructor_constructsWithNullMinCoords_throws() {
    int width = 40, height = 40;
    Vector2D minCoords = null;
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with null maxCoords throws")
  void constructor_constructsWithNullMaxCoords_throws() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = null;

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with minCoords greater than maxCoords throws")
  void constructor_constructsWithMinCoordsGreaterThanMaxCoords_throws() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(1, 1);
    Vector2D maxCoords = new Vector2D(0, 0);

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with minCoords equal to maxCoords throws")
  void constructor_constructsWithMinCoordsEqualToMaxCoords_throws() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(1, 1);
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class, () -> new ChaosCanvas(width, height, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test getPixel works")
  void getPixel_getsPixel_works() {
    Vector2D point = new Vector2D(0.5, 0.5);

    int result = chaosCanvas.getPixel(point);
  }
  
  @Test
  @DisplayName("Test getPixel throws when request is outside boundaries")
  void getPixel_getsPixelOutsideBoundaries_throws() {
    Vector2D outsidePoint = new Vector2D(1.1, 1.1);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> chaosCanvas.getPixel(outsidePoint));
  }

  @Test
  @DisplayName("Test putPixel works")
  void putPixel_putsPixel_works() {
    Vector2D point = new Vector2D(0.5, 0.5);

    chaosCanvas.putPixel(point);
  }

  @Test
  @DisplayName("Test putPixel with negative x coordinate does not put pixel")
  void putPixel_putsPixelWithNegativeXCoordinate_doesNotPutPixel() {
    Vector2D point = new Vector2D(-0.5, 0.5);

    chaosCanvas.putPixel(point);
  }

  @Test
  @DisplayName("Test putPixel with negative y coordinate does not put pixel")
  void putPixel_putsPixelWithNegativeYCoordinate_doesNotPutPixel() {
    Vector2D point = new Vector2D(0.5, -0.5);

    chaosCanvas.putPixel(point);
  }

  @Test
  @DisplayName("Test putPixel with x coordinate greater than width does not put pixel")
  void putPixel_putsPixelWithXCoordinateGreaterThanWidth_doesNotPutPixel() {
    Vector2D point = new Vector2D(40.5, 0.5);

    chaosCanvas.putPixel(point);
  }

  @Test
  @DisplayName("Test putPixel with y coordinate greater than height does not put pixel")
  void putPixel_putsPixelWithYCoordinateGreaterThanHeight_doesNotPutPixel() {
    Vector2D point = new Vector2D(0.5, 40.5);

    chaosCanvas.putPixel(point);
  }

  @Test
  @DisplayName("Test getCanvas works")
  void getCanvas_getsCanvas_works() {
    Vector2D point = new Vector2D(0.5, 0.5);
    chaosCanvas.putPixel(point);

    int[][] result = chaosCanvas.getCanvas();
    Vector2D indices = transformCoordsToIndices.transform(point);
    int i = (int) Math.round(indices.getX0());
    int j = (int) Math.round(indices.getX1());

    assertEquals(1, result[i][j]);
  }

  @Test
  @DisplayName("Test clear works")
  void clear_clearsCanvas_works() {
    Vector2D point = new Vector2D(0.5, 0.5);
    chaosCanvas.putPixel(point);
    chaosCanvas.clear();
    assertEquals(0, chaosCanvas.getPixel(point));
  }
}
