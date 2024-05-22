package edu.ntnu.stud.views.components;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Button;

/**
 * This class represents a Button with customizable styles. It extends the Button class from the
 * JavaFX library. The class includes a Builder for setting the text, preferred width and height,
 * style, and classes of the StyledButton.
 */
public class StyledButton extends Button {

  /**
   * Constructor for the StyledButton class. Initializes the StyledButton with the specified text,
   * preferred width and height, style, and classes.
   *
   * @param builder the Builder used to set the text, preferred width and height, style, and classes
   *     of the StyledButton
   */
  private StyledButton(Builder builder) {
    setText(builder.text);
    setPrefWidth(builder.prefWidth);
    setPrefHeight(builder.prefHeight);
    setStyle(builder.style);
    getStyleClass().addAll(builder.classes);
  }

  /**
   * This class represents a Builder for the StyledButton class. It includes methods for setting the
   * text, preferred width and height, style, and classes of the StyledButton.
   */
  public static class Builder {
    private String text;

    private double prefWidth = 100;
    private double prefHeight = 30;
    private String style = "";
    private Set<String> classes = new HashSet<>();

    /**
     * Constructor for the Builder class. Initializes the Builder with the specified text.
     *
     * @param text the text of the StyledButton
     */
    public Builder(String text) {
      this.text = text;
    }

    /**
     * Adds a class to the StyledButton if it does not already exist.
     *
     * @param className the class to add
     */
    private void addClassIfNotExists(String className) {
      if (!classes.contains(className)) {
        classes.add(className);
      }
    }

    /**
     * Adds the accent class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder accent() {
      addClassIfNotExists("accent");
      return this;
    }

    /**
     * Adds the success class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder success() {
      addClassIfNotExists("success");
      return this;
    }

    /**
     * Adds the warning class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder danger() {
      addClassIfNotExists("danger");
      return this;
    }

    /**
     * Adds the flat class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder flat() {
      addClassIfNotExists("flat");
      return this;
    }

    /**
     * Adds the small class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder small() {
      addClassIfNotExists("small");
      return this;
    }

    /**
     * Adds the large class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder large() {
      addClassIfNotExists("large");
      return this;
    }

    /**
     * Adds the rounded class to the StyledButton.
     *
     * @return this Builder instance
     */
    public Builder rounded() {
      addClassIfNotExists("rounded");
      return this;
    }

    /**
     * Sets the preferred width of the StyledButton.
     *
     * @param prefWidth the preferred width of the StyledButton
     * @return this Builder instance
     */
    public Builder prefWidth(double prefWidth) {
      this.prefWidth = prefWidth;
      return this;
    }

    /**
     * Sets the preferred height of the StyledButton.
     *
     * @param prefHeight the preferred height of the StyledButton
     * @return this Builder instance
     */
    public Builder prefHeight(double prefHeight) {
      this.prefHeight = prefHeight;
      return this;
    }

    /**
     * Sets the style of the StyledButton.
     *
     * @param style the style of the StyledButton
     * @return this Builder instance
     */
    public Builder style(String style) {
      this.style = style;
      return this;
    }

    /**
     * Builds a new StyledButton instance with the set parameters.
     *
     * @return a new StyledButton instance
     */
    public StyledButton build() {
      return new StyledButton(this);
    }
  }
}
