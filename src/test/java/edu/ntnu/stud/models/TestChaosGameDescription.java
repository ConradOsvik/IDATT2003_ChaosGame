package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the ChaosGameDescription class. It tests the functionality of
 * the ChaosGameDescription class methods and constructors. Each test method in this class is
 * annotated with the @Test annotation. The setup method, annotated with @BeforeEach, is used to
 * initialize the ChaosGameDescription object used in the tests.
 *
 * @see edu.ntnu.stud.models.ChaosGameDescription
 */
class TestChaosGameDescription {

  private ChaosGameDescription chaosGameDescription;

  @BeforeEach
  void setUp() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    Transform2D transform2 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    Transform2D transform3 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3);

    this.chaosGameDescription = new ChaosGameDescription(transforms, minCoords, maxCoords);
  }

  @Test
  @DisplayName("Test constructor works")
  void constructor_constructsChaosGameDescription_works() {
    assertNotNull(this.chaosGameDescription);
  }

  @Test
  @DisplayName("Test constructor works with empty (zero) transformations")
  void constructor_constructsEmptyTransformations_works() {
    try {
      Vector2D minCoords = new Vector2D(0, 0);
      Vector2D maxCoords = new Vector2D(1, 1);
      ChaosGameDescription chaosGameDescription =
          new ChaosGameDescription(Collections.emptyList(), minCoords, maxCoords);
    } catch (Exception e) {
      fail("Should not throw exception");
    }
  }

  @Test
  @DisplayName("Test constructor with null transforms throws")
  void constructor_constructsWithNullTransforms_throws() {
    List<Transform2D> transforms = null;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class,
        () -> new ChaosGameDescription(transforms, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with null minCoords throws")
  void constructor_constructsWithNullMinCoords_throws() {
    List<Transform2D> transforms = List.of();
    Vector2D minCoords = null;
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class,
        () -> new ChaosGameDescription(transforms, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with null maxCoords throws")
  void constructor_constructsWithNullMaxCoords_throws() {
    List<Transform2D> transforms = List.of();
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = null;

    assertThrows(
        IllegalArgumentException.class,
        () -> new ChaosGameDescription(transforms, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with minCoords greater than maxCoords throws")
  void constructor_constructsWithMinCoordsGreaterThanMaxCoords_throws() {
    List<Transform2D> transforms = List.of();
    Vector2D minCoords = new Vector2D(1, 1);
    Vector2D maxCoords = new Vector2D(0, 0);

    assertThrows(
        IllegalArgumentException.class,
        () -> new ChaosGameDescription(transforms, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test constructor with minCoords equal to maxCoords throws")
  void constructor_constructsWithMinCoordsEqualToMaxCoords_throws() {
    List<Transform2D> transforms = List.of();
    Vector2D minCoords = new Vector2D(1, 1);
    Vector2D maxCoords = new Vector2D(1, 1);

    assertThrows(
        IllegalArgumentException.class,
        () -> new ChaosGameDescription(transforms, minCoords, maxCoords));
  }

  @Test
  @DisplayName("Test getTransforms works")
  void getTransforms_getsTransforms_works() {
    assertNotNull(this.chaosGameDescription.getTransforms());
  }

  @Test
  @DisplayName("Test getMinCoords works")
  void getMinCoords_getsMinCoords_works() {
    assertNotNull(this.chaosGameDescription.getMinCoords());
  }

  @Test
  @DisplayName("Test getMaxCoords works")
  void getMaxCoords_getsMaxCoords_works() {
    assertNotNull(this.chaosGameDescription.getMaxCoords());
  }

  @Test
  @DisplayName("Test getTransforms returns correct value")
  void getTransforms_getsTransforms_returnsCorrectValue() {
    Transform2D transform1 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    Transform2D transform2 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    Transform2D transform3 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3);

    assertEquals(transforms, chaosGameDescription.getTransforms());
  }

  @Test
  @DisplayName("Test getMinCoords returns correct value")
  void getMinCoords_getsMinCoords_returnsCorrectValue() {
    Vector2D minCoords = new Vector2D(0, 0);

    assertEquals(minCoords, chaosGameDescription.getMinCoords());
  }

  @Test
  @DisplayName("Test getMaxCoords returns correct value")
  void getMaxCoords_getsMaxCoords_returnsCorrectValue() {
    Vector2D maxCoords = new Vector2D(1, 1);

    assertEquals(maxCoords, chaosGameDescription.getMaxCoords());
  }
}
