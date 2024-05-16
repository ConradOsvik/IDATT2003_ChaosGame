package edu.ntnu.stud.models;

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

public class ChaosGameFileHandler {

  /**
   * Removes the comment from a line.
   *
   * @param line the line to remove the comment from.
   * @return a line without the comment.
   */
  private String removeComment(String line) {
    if (line.contains("#")) {
      return line.substring(0, line.indexOf("#")).trim();
    }
    return line;
  }

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

    try (Scanner lineScanner = new Scanner(file).useLocale(Locale.ENGLISH)) {
      lineScanner.useDelimiter("\\n");

      String transformType = removeComment(lineScanner.nextLine().trim());
      Vector2D lowerLeft = parseVector2D(removeComment(lineScanner.nextLine().trim()));
      Vector2D upperRight = parseVector2D(removeComment(lineScanner.nextLine().trim()));

      List<Transform2D> transforms = new ArrayList<>();
      if (transformType.equals("Affine2D")) {
        readAffineTransforms(lineScanner, transforms);
      }
      if (transformType.equals("Julia")) {
        readJuliaTransforms(lineScanner, transforms);
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
   * Parses a Vector2D from a line.
   *
   * @param line the line to parse the Vector2D from.
   * @return a Vector2D parsed from the line.
   */
  private Vector2D parseVector2D(String line) {
    try (Scanner scanner = new Scanner(line)) {
      scanner.useDelimiter(", ");
      return new Vector2D(scanner.nextDouble(), scanner.nextDouble());
    }
  }

  /**
   * Reads a list of AffineTransform2D objects from a Scanner.
   *
   * @param lineScanner The Scanner to read from.
   * @param transforms The list to add the read transforms to.
   */
  private void readAffineTransforms(Scanner lineScanner, List<Transform2D> transforms)
      throws FileHandlingException {
    while (lineScanner.hasNextLine()) {
      String line = removeComment(lineScanner.nextLine().trim());
      try (Scanner scanner = new Scanner(line)) {
        scanner.useDelimiter(", ");
        while (scanner.hasNext()) {
          double[] parts = new double[6];
          for (int i = 0; i < 6; i++) {
            if (!scanner.hasNext()) {
              throw new FileHandlingException(
                  "The file is not formatted correctly. Expected 6 parts for each transform.");
            }
            parts[i] = scanner.nextDouble();
          }

          Matrix2x2 matrix = new Matrix2x2(parts[0], parts[1], parts[2], parts[3]);
          Vector2D vector = new Vector2D(parts[4], parts[5]);
          transforms.add(new AffineTransform2D(matrix, vector));
        }
      }
    }
  }

  /**
   * Reads a list of JuliaTransform objects from a Scanner.
   *
   * @param lineScanner The Scanner to read from.
   * @param transforms The list to add the read transforms to.
   */
  private void readJuliaTransforms(Scanner lineScanner, List<Transform2D> transforms)
      throws FileHandlingException {
    while (lineScanner.hasNextLine()) {
      String line = removeComment(lineScanner.nextLine().trim());
      try (Scanner scanner = new Scanner(line)) {
        scanner.useDelimiter(", ");
        while (scanner.hasNext()) {
          double[] parts = new double[2];
          for (int i = 0; i < 2; i++) {
            if (!scanner.hasNext()) {
              throw new FileHandlingException(
                  "The file is not formatted correctly. Expected 2 parts for each Julia"
                      + " transform.");
            }
            parts[i] = scanner.nextDouble();
          }

          Complex c = new Complex(parts[0], parts[1]);
          int sign = (int) Math.signum(c.getImaginary());
          transforms.add(new JuliaTransform(c, sign));
        }
      }
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
    File file = new File(path);
    File parentDir = file.getParentFile();
    if (parentDir != null) {
      if (!parentDir.exists()) {
        if (!parentDir.mkdirs()) {
          throw new FileHandlingException("Could not create directory: " + parentDir);
        }
      }
    }

    try {
      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (IOException e) {
      throw new FileHandlingException("An error occurred creating the file");
    }

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
   * Formats a number to the correct format. for numbers less than 1 and greater than 1, it should
   * be on the form sign.digit. for example .5 or -.5. For numbers that are integers, it should be
   * formatted as an integer instead of a double.
   *
   * @param number the number to be formated.
   * @return the formatted number.
   */
  private String formatNumber(double number) {
    // Convert doubles to ints if they are ints
    if (number == (int) number) {
      return String.valueOf((int) number);
    } else {
      String formattedNumber = String.valueOf(number);

      // Remove leading zeros
      if (formattedNumber.startsWith("0.")) {
        formattedNumber = formattedNumber.substring(1);
      } else if (formattedNumber.startsWith("-0.")) {
        formattedNumber = "-" + formattedNumber.substring(2);
      }
      return formattedNumber;
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
    writer.write(formatNumber(minCoords.getX0()) + ", " + formatNumber(minCoords.getX1()) + "\n");
    writer.write(formatNumber(maxCoords.getX0()) + ", " + formatNumber(maxCoords.getX1()) + "\n");
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
      writer.write(
          formatNumber(affine.getMatrix().a00())
              + ", "
              + formatNumber(affine.getMatrix().a01())
              + ", "
              + formatNumber(affine.getMatrix().a10())
              + ", "
              + formatNumber(affine.getMatrix().a11())
              + ", "
              + formatNumber(affine.getVector().getX0())
              + ", "
              + formatNumber(affine.getVector().getX1())
              + "\n");
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
      writer.write(
          formatNumber(julia.getC().getReal())
              + ", "
              + formatNumber(julia.getC().getImaginary())
              + "\n");
    }
  }
}
