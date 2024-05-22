package edu.ntnu.stud.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the InputHandler class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
class TestInputHandler {

  private final InputStream standardIn = System.in;
  private final PrintStream standardOut = System.out;

  @AfterEach
  void cleanup() {
    System.setIn(standardIn);
    System.setOut(standardOut);
  }

  private InputHandler act(String input) {
    InputStream in = new ByteArrayInputStream((input + "\n").getBytes());
    System.setIn(in);

    return new InputHandler();
  }

  @Test
  @DisplayName("Test getString works")
  void getString_TextIsString_ReturnsText() {
    String input = "text input";

    String result = assertDoesNotThrow(() -> act(input).getString());

    assertEquals(input, result);
  }

  @Test
  @DisplayName("Test getInt returns int")
  void getInt_TextIsInt_ReturnsInt() {
    String input = "0";

    int result = assertDoesNotThrow(() -> act(input).getInt());

    assertEquals(0, result);
  }

  @Test
  @DisplayName("Test getInt throws exception for string")
  void getInt_TextIsNotInt_ThrowsException() {
    String input = "not an int";

    assertThrows(InvalidFormatException.class, () -> act(input).getInt());
  }

  @Test
  @DisplayName("Test getDouble returns double")
  void getDouble_TextIsDouble_ReturnsDouble() {
    String input = "0.0";

    double result = assertDoesNotThrow(() -> act(input).getDouble());

    assertEquals(0.0, result);
  }

  @Test
  @DisplayName("Test getDouble throws exception for string")
  void getDouble_TextIsNotDouble_ThrowsException() {
    String input = "not a double";

    assertThrows(InvalidFormatException.class, () -> act(input).getDouble());
  }

  @Test
  @DisplayName("Test getTransformType returns transform type for affine")
  void getTransformType_TextIsAffine_ReturnsAffine() {
    String input = "affine";

    String result = assertDoesNotThrow(() -> act(input).getTransformType());

    assertEquals("affine", result);
  }

  @Test
  @DisplayName("Test getTransformType returns transform type for julia")
  void getTransformType_TextIsJulia_ReturnsJulia() {
    String input = "julia";

    String result = assertDoesNotThrow(() -> act(input).getTransformType());

    assertEquals("julia", result);
  }

  @Test
  @DisplayName("Test getTransformType throws exception for string")
  void getTransformType_TextIsNotAffineOrJulia_ThrowsException() {
    String input = "not a transform type";

    assertThrows(InvalidInputException.class, () -> act(input).getTransformType());
  }

  @Test
  @DisplayName("Test getSign returns sign for 1")
  void getSign_TextIs1_Returns1() {
    String input = "1";

    int result = assertDoesNotThrow(() -> act(input).getSign());

    assertEquals(1, result);
  }

  @Test
  @DisplayName("Test getSign returns sign for -1")
  void getSign_TextIsMinus1_ReturnsMinus1() {
    String input = "-1";

    int result = assertDoesNotThrow(() -> act(input).getSign());

    assertEquals(-1, result);
  }

  @Test
  @DisplayName("Test getSign throws exception for string")
  void getSign_TextIsString_ThrowsException() {
    String input = "not a sign";

    assertThrows(InvalidFormatException.class, () -> act(input).getSign());
  }

  @Test
  @DisplayName("Test getSign throws exception for 2")
  void getSign_TextIsNot1OrMinus1_ThrowsException() {
    String input = "2";

    assertThrows(InvalidInputException.class, () -> act(input).getSign());
  }
}
