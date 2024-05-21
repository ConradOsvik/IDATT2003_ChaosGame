package edu.ntnu.stud.views.components;

import edu.ntnu.stud.views.components.StyledButton.Builder;
import javafx.scene.control.TextField;

public class NumberField extends TextField {
  private NumberField(Builder builder) {
    setPrefWidth(builder.prefWidth);
  }

  public static class Builder {
    private double prefWidth = 100;
    private double value = 0;
    private boolean valueSet = false;
    private String label;

    public Builder(String label) {
      this.label = label;
    }

    public Builder prefWidth(double prefWidth) {
      this.prefWidth = prefWidth;
      return this;
    }

    public Builder value(double value) {
      this.value = value;
      this.valueSet = true;
      return this;
    }

    public NumberField build() {
      NumberField numberField = new NumberField(this);
      numberField.setPromptText(label);
      if(valueSet){
      numberField.setText(String.valueOf(value));
      }

      return numberField;
    }
  }

  public double getValue() {
    return Double.parseDouble(getText());
  }

  @Override
  public void replaceText(int start, int end, String text) {
    if (validate(text)) {
      super.replaceText(start, end, text);
    }
  }

  @Override
  public void replaceSelection(String text) {
    if (validate(text)) {
      super.replaceSelection(text);
    }
  }

  private boolean validate(String text) {
    return text.matches("[-]?[0-9]*([.][0-9]*)?");
  }
}
