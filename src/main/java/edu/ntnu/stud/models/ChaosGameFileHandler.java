package edu.ntnu.stud.models;

import static java.lang.Math.signum;

import edu.ntnu.stud.exceptions.FileHandlingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is responsible for handling file operations related to the ChaosGame. It can read a
 * ChaosGameDescription from a file and write a ChaosGameDescription to a file.
 */
public class ChaosGameFileHandler {

  /**
   * Reads a ChaosGameDescription from a file.
   *
   * @param path The path of the file to read from.
   * @return The ChaosGameDescription read from the file.
   * @throws FileHandlingException If there is an error reading the file.
   */
  public ChaosGameDescription readFromFile(String path) throws FileHandlingException {
    File file = new File(path);
    if (!file.exists()) {
      throw new FileHandlingException("File not found");
    }

    try (Scanner scanner = new Scanner(file).useLocale(Locale.ENGLISH)) {
      scanner.useDelimiter("[,\\n]"); // ",|\\n"

      String transformType = scanner.next().trim();
      Vector2D lowerLeft = new Vector2D(scanner.nextDouble(), scanner.nextDouble());
      Vector2D upperRight = new Vector2D(scanner.nextDouble(), scanner.nextDouble());

      List<Transform2D> transforms = new ArrayList<>();
      if (transformType.equals("Affine2D")) {
        readAffineTransforms(scanner, transforms);
      }
      if (transformType.equals("Julia")) {
        readJuliaTransforms(scanner, transforms);
      }

      return new ChaosGameDescription(transforms, lowerLeft, upperRight);
    } catch (InputMismatchException e) {
      throw new FileHandlingException("The file is not formatted correctly");
    } catch (NoSuchElementException e) {
      throw new FileHandlingException("The file is missing elements");
    } catch (Exception e) {
      throw new FileHandlingException("An error occurred reading the file");
    }
  }

  /**
   * Reads a list of AffineTransform2D objects from a Scanner.
   *
   * @param scanner The Scanner to read from.
   * @param transforms The list to add the read transforms to.
   */
  private void readAffineTransforms(Scanner scanner, List<Transform2D> transforms) {
    while (scanner.hasNext()) {
      Matrix2x2 matrix =
          new Matrix2x2(
              scanner.nextDouble(),
              scanner.nextDouble(),
              scanner.nextDouble(),
              scanner.nextDouble());
      Vector2D vector = new Vector2D(scanner.nextDouble(), scanner.nextDouble());
      transforms.add(new AffineTransform2D(matrix, vector));
    }
  }

  /**
   * Reads a list of JuliaTransform objects from a Scanner.
   *
   * @param scanner The Scanner to read from.
   * @param transforms The list to add the read transforms to.
   */
  private void readJuliaTransforms(Scanner scanner, List<Transform2D> transforms) {
    while (scanner.hasNext()) {
      Complex c = new Complex(scanner.nextDouble(), scanner.nextDouble());
      int sign = (int) signum(c.getImaginary());
      transforms.add(new JuliaTransform(c, sign));
    }
  }

  /**
   * Writes a ChaosGameDescription to a file.
   *
   * @param description The ChaosGameDescription to write.
   * @param path The path of the file to write to.
   * @throws FileHandlingException If there is an error writing to the file.
   */
  public void writeToFile(ChaosGameDescription description, String path)
      throws FileHandlingException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      if (description.getTransforms().getFirst() instanceof AffineTransform2D) {
        writer.write("Affine2D\n");
        writeCoordinates(writer, description);
        writeAffineTransforms(writer, description.getTransforms());
      } else if (description.getTransforms().getFirst() instanceof JuliaTransform) {
        writer.write("Julia\n");
        writeCoordinates(writer, description);
        writeJuliaTransforms(writer, description.getTransforms());
      }
    } catch (Exception e) {
      throw new FileHandlingException("An error occurred writing to the file");
    }
  }

  /**
   * Writes the coordinates of a ChaosGameDescription to a BufferedWriter.
   *
   * @param writer The BufferedWriter to write to.
   * @param description The ChaosGameDescription whose coordinates to write.
   * @throws IOException If there is an error writing to the BufferedWriter.
   */
  private void writeCoordinates(BufferedWriter writer, ChaosGameDescription description)
      throws IOException {
    Vector2D minCoords = description.getMinCoords();
    Vector2D maxCoords = description.getMaxCoords();
    writer.write(minCoords.getX0() + ", " + minCoords.getX1() + "\n");
    writer.write(maxCoords.getX0() + ", " + maxCoords.getX1() + "\n");
  }

  /**
   * Writes a list of AffineTransform2D objects to a BufferedWriter.
   *
   * @param writer The BufferedWriter to write to.
   * @param transforms The list of transforms to write.
   * @throws IOException If there is an error writing to the BufferedWriter.
   */
  private void writeAffineTransforms(BufferedWriter writer, List<Transform2D> transforms)
      throws IOException {
    for (Transform2D transform : transforms) {
      AffineTransform2D affine = (AffineTransform2D) transform;
      writer.write(affine.getMatrix().a00() + ", " + affine.getMatrix().a01() + ", " + affine.getMatrix().a10() + ", " + affine.getMatrix().a11() + ", " + affine.getVector().getX0() + ", " + affine.getVector().getX1() + "\n");
    }
  }

  /**
   * Writes a list of JuliaTransform objects to a BufferedWriter.
   *
   * @param writer The BufferedWriter to write to.
   * @param transforms The list of transforms to write.
   * @throws IOException If there is an error writing to the BufferedWriter.
   */
  private void writeJuliaTransforms(BufferedWriter writer, List<Transform2D> transforms)
      throws IOException {
    for (Transform2D transform : transforms) {
      JuliaTransform julia = (JuliaTransform) transform;
      writer.write(julia.getC().getReal() + ", " + julia.getC().getImaginary() + "\n");
    }
  }
}
