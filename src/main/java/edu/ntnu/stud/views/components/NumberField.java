package edu.ntnu.stud.views.components;

import javafx.scene.control.TextField;

/**
 * This class represents a TextField specifically designed to handle numeric input. It extends the
 * TextField class from the JavaFX library. The class includes a Builder for setting the preferred
 * width and initial value of the NumberField. The NumberField only accepts numeric input, including
 * negative numbers and decimal numbers.
 */
public class NumberField extends TextField {

  /**
   * Constructor for the NumberField class. Initializes the NumberField with the specified preferred
   * width.
   *
   * @param builder the Builder used to set the preferred width and initial value of the
   *                NumberField
   */
  private NumberField(Builder builder) {
    setPrefWidth(builder.prefWidth);
  }

  /**
   * This class represents a Builder for the NumberField class. It includes methods for setting the
   * preferred width and initial value of the NumberField.
   */
  public static class Builder {

    private double prefWidth = 100;
    private double value = 0;
    private boolean valueSet = false;
    private String label;

    public Builder(String label) {
      this.label = label;
    }

    /**
     * Sets the preferred width of the NumberField.
     *
     * @param prefWidth the preferred width of the NumberField
     * @return this Builder instance
     */
    public Builder prefWidth(double prefWidth) {
      this.prefWidth = prefWidth;
      return this;
    }

    /**
     * Sets the initial value of the NumberField.
     *
     * @param value the initial value of the NumberField
     * @return this Builder instance
     */
    public Builder value(double value) {
      this.value = value;
      this.valueSet = true;
      return this;
    }

    /**
     * Builds a new NumberField instance with the set parameters.
     *
     * @return a new NumberField instance
     */
    public NumberField build() {
      NumberField numberField = new NumberField(this);
      numberField.setPromptText(label);
      if (valueSet) {
        numberField.setText(String.valueOf(value));
      }

      return numberField;
    }
  }

  /**
   * Returns the numeric value of the NumberField.
   *
   * @return the numeric value of the NumberField
   */
  public double getValue() {
    return Double.parseDouble(getText());
  }

  /**
   * Replaces a portion of the text in the NumberField.
   * Only allows the replacement if the resulting text is a valid number.
   *
   * @param start the start index of the text to replace
   * @param end the end index of the text to replace
   * @param text the text to insert
   */
  @Override
  public void replaceText(int start, int end, String text) {
    if (validate(text)) {
      super.replaceText(start, end, text);
    }
  }

  /**
   * Replaces the currently selected text in the NumberField.
   * Only allows the replacement if the resulting text is a valid number.
   *
   * @param text the text to insert
   */
  @Override
  public void replaceSelection(String text) {
    if (validate(text)) {
      super.replaceSelection(text);
    }
  }

  /**
   * Validates the specified text.
   * Returns true if the text is a valid number, and false otherwise.
   *
   * @param text the text to validate
   * @return true if the text is a valid number, and false otherwise
   */
  private boolean validate(String text) {
    return text.matches("[-]?[0-9]*([.][0-9]*)?");
  }
}
