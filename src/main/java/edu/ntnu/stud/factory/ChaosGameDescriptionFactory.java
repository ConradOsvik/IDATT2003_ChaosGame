package edu.ntnu.stud.factory;

import edu.ntnu.stud.models.AffineTransform2D;
import edu.ntnu.stud.models.ChaosGameDescription;
import edu.ntnu.stud.models.Complex;
import edu.ntnu.stud.models.JuliaTransform;
import edu.ntnu.stud.models.Matrix2x2;
import edu.ntnu.stud.models.Transform2D;
import edu.ntnu.stud.models.Vector2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for creating different types of ChaosGameDescription objects. It
 * provides static factory methods for creating descriptions for different types of chaos games,
 * such as the Sierpinski Triangle, Barnsley Fern, and Julia Set.
 */
public class ChaosGameDescriptionFactory {

  /**
   * Creates a ChaosGameDescription object based on the provided description type.
   *
   * @param descriptionType The type of the chaos game description to create. It can be "Sierpinski
   *                        Triangle", "Barnsley Fern", or "Julia Set".
   * @return A ChaosGameDescription object of the specified type, or null if the type is not
   * recognized.
   */
  public static ChaosGameDescription createDescription(String descriptionType) {
    return switch (descriptionType) {
      case "Sierpinski Triangle" -> createSierpinskiDescription();
      case "Barnsley Fern" -> createBarnsleyDescription();
      case "Julia Set" -> createJuliaDescription();
      default -> null;
    };
  }

  /**
   * Creates a ChaosGameDescription object for the Sierpinski Triangle.
   *
   * @return A ChaosGameDescription object for the Sierpinski Triangle.
   */
  public static ChaosGameDescription createSierpinskiDescription() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector2 = new Vector2D(0.5, 0);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector3 = new Vector2D(0.25, 0.5);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);
  }

  /**
   * Creates a ChaosGameDescription object for the Barnsley Fern.
   *
   * @return A ChaosGameDescription object for the Barnsley Fern.
   */
  public static ChaosGameDescription createBarnsleyDescription() {
    Vector2D minCoords = new Vector2D(-2.65, 0);
    Vector2D maxCoords = new Vector2D(2.65, 10);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0, 0, 0, 0.16);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.85, 0.04, -0.04, 0.85);
    Vector2D vector2 = new Vector2D(0, 1.6);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.2, -0.26, 0.23, 0.22);
    Vector2D vector3 = new Vector2D(0, 1.6);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    Matrix2x2 matrix4 = new Matrix2x2(-0.15, 0.28, 0.26, 0.24);
    Vector2D vector4 = new Vector2D(0, 0.44);
    AffineTransform2D transform4 = new AffineTransform2D(matrix4, vector4);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);
    transforms.add(transform4);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);
  }

  /**
   * Creates a ChaosGameDescription object for the Julia Set.
   *
   * @return A ChaosGameDescription object for the Julia Set.
   */
  public static ChaosGameDescription createJuliaDescription() {
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    List<Transform2D> transforms = new ArrayList<>();

    JuliaTransform transform1 = new JuliaTransform(new Complex(-0.74543, 0.11301), 1);
    JuliaTransform transform2 = new JuliaTransform(new Complex(-0.74543, 0.11301), -1);

    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);
  }
}
