package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.exceptions.FileHandlingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the functionality of the ChaosGameFileHandler class. It includes tests
 * for reading and writing of ChaosGameDescription objects.
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
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  @DisplayName("Test read and write of ChaosGameFileHandler")
  void readAndWrite_works() throws Exception {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    Transform2D transform2 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    Transform2D transform3 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3);
    ChaosGameDescription originalDescription =
        new ChaosGameDescription(transforms, minCoords, maxCoords);

    chaosGameFileHandler.writeToFile(originalDescription, testFile);

    ChaosGameDescription readDescription = chaosGameFileHandler.readFromFile(testFile);

    assertEquals(originalDescription, readDescription);
  }

  @Test
  @DisplayName("Test writing and then reading a ChaosGameDescription with AffineTransforms")
  void readAndWriteAffineTransforms_works() throws Exception {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    Transform2D transform2 =
        new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    List<Transform2D> transforms = Arrays.asList(transform1, transform2);
    ChaosGameDescription description = new ChaosGameDescription(transforms, minCoords, maxCoords);

    chaosGameFileHandler.writeToFile(description, testFile);
    ChaosGameDescription readDescription = chaosGameFileHandler.readFromFile(testFile);

    assertEquals(description, readDescription);
  }

  @Test
  @DisplayName("Test writing and then reading a ChaosGameDescription with JuliaTransforms")
  void readAndWriteJuliaTransforms_works() throws Exception {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Transform2D transform1 = new JuliaTransform(new Complex(0.2, 0.5), 1);
    List<Transform2D> transforms = Arrays.asList(transform1);
    ChaosGameDescription description = new ChaosGameDescription(transforms, minCoords, maxCoords);

    chaosGameFileHandler.writeToFile(description, testFile);
    ChaosGameDescription readDescription = chaosGameFileHandler.readFromFile(testFile);

    assertEquals(description, readDescription);
  }

  @Test
  @DisplayName("Test readFromFile throws for non-existent file")
  void readFromFile_readsNonExistentFile_throws() {
    Exception exception =
        assertThrows(
            FileHandlingException.class,
            () -> {
              chaosGameFileHandler.readFromFile("nonexistentfile.txt");
            });
    assertTrue(exception.getMessage().contains("File not found"));
  }

  @Test
  @DisplayName("Test readFromFile throws for incorrect file format")
  void readFromFile_readsIncorrectFileFormat_throws() throws Exception {
    String incorrectData = "Affine2D\n0,0\n1,1\n0.5,0.5";
    BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
    writer.write(incorrectData);
    writer.close();

    Exception exception =
        assertThrows(
            FileHandlingException.class,
            () -> {
              chaosGameFileHandler.readFromFile(testFile);
            });
  }
}
