package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the ChaosGame class. It tests the functionality of the
 * ChaosGame class methods and constructors. Each test method in this class is annotated with the
 * @Test annotation. The setup method, annotated with @BeforeEach, is used to initialize the
 * ChaosGame object used in the tests.
 *
 * @see edu.ntnu.stud.models.ChaosGame
 */
class TestChaosGame {

  private ChaosGame chaosGame;

  @BeforeEach
  void setUp() {
    int width = 40, height = 40;
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0, 0));
    Transform2D transform2 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.25, 0.5));
    Transform2D transform3 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5),
        new Vector2D(0.5, 0));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3);

    ChaosGameDescription description = new ChaosGameDescription(transforms, minCoords, maxCoords);
    this.chaosGame = new ChaosGame(description, width, height);
  }

  @Test
  @DisplayName("Test constructor works")
  void constructor_constructsChaosGame_works() {
    assertNotNull(this.chaosGame);
  }

  @Test
  @DisplayName("Test constructor with null description throws")
  void constructor_constructsWithNullDescription_throws() {
    ChaosGameDescription description = null;
    int width = 40, height = 40;

    assertThrows(IllegalArgumentException.class, () -> new ChaosGame(description, width, height));
  }

  @Test
  @DisplayName("Test constructor with negative width throws")
  void constructor_constructsWithNegativeWidth_throws() {
    ChaosGameDescription description = new ChaosGameDescription(
        List.of(), new Vector2D(0, 0), new Vector2D(1, 1));
    int width = -40, height = 40;

    assertThrows(IllegalArgumentException.class, () -> new ChaosGame(description, width, height));
  }

  @Test
  @DisplayName("Test constructor with negative height throws")
  void constructor_constructsWithNegativeHeight_throws() {
    ChaosGameDescription description = new ChaosGameDescription(
        List.of(), new Vector2D(0, 0), new Vector2D(1, 1));
    int width = 40, height = -40;

    assertThrows(IllegalArgumentException.class, () -> new ChaosGame(description, width, height));
  }

  @Test
  @DisplayName("Test runSteps works")
  void runSteps_runsSteps_works() {
    this.chaosGame.runSteps(100000);
  }
}
