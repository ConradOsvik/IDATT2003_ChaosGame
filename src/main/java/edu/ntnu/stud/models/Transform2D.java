package edu.ntnu.stud.models;

/**
 * This interface represents a 2D transformation. It provides a method to transform a 2D vector.
 * Implementations of this interface can define their own specific transformations, such as affine
 * transformations or Julia transformations.
 */
public interface Transform2D {

  /**
   * Transforms the given 2D vector according to the specific transformation defined by the
   * implementation.
   *
   * @param vec the 2D vector to be transformed
   * @return the transformed 2D vector
   */
  Vector2D transform(Vector2D vec);
}
