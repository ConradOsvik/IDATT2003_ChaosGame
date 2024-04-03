package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the ChaosGameFileHandler class.
 * It includes tests for reading and writing of ChaosGameDescription objects.
 */
class TestChaosGameFileHandler {
  private ChaosGameFileHandler chaosGameFileHandler;
  private final String testFile = "test.txt";
  @BeforeEach
  void setUp() {
    this.chaosGameFileHandler = new ChaosGameFileHandler();
  }

  @AfterEach
  void tearDown() {
    File file = new File(testFile);
    if(file.exists()){
      file.delete();
    }
  }

  @Test
  @DisplayName("Test read and write of ChaosGameFileHandler")
  void testReadWrite() throws Exception {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    Transform2D transform2 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    Transform2D transform3 = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3);
    ChaosGameDescription originalDescription = new ChaosGameDescription(transforms, minCoords, maxCoords);

    chaosGameFileHandler.writeToFile(originalDescription, testFile);

    ChaosGameDescription readDescription = chaosGameFileHandler.readFromFile(testFile);

    assertEquals(originalDescription, readDescription);
  }
}
