package edu.ntnu.stud.views.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.Button;

public class StyledButton extends Button {
  private StyledButton(Builder builder) {
    setText(builder.text);
    setPrefWidth(builder.prefWidth);
    setPrefHeight(builder.prefHeight);
    setStyle(builder.style);
    getStyleClass().addAll(builder.classes);
  }

  public static class Builder {
    // Required parameters
    private String text;

    // Optional parameters - initialized to default values
    private double prefWidth = 100;
    private double prefHeight = 30;
    private String style = "";
    private Set<String> classes = new HashSet<>();

    public Builder(String text) {
      this.text = text;
    }

    private void addClassIfNotExists(String className) {
      if (!classes.contains(className)) {
        classes.add(className);
      }
    }

    public Builder accent(){
      addClassIfNotExists("accent");
      return this;
    }

    public Builder success(){
      addClassIfNotExists("success");
      return this;
    }

    public Builder danger(){
      addClassIfNotExists("danger");
      return this;
    }

    public Builder flat(){
      addClassIfNotExists("flat");
      return this;
    }

    public Builder small(){
      addClassIfNotExists("small");
      return this;
    }

    public Builder large(){
      addClassIfNotExists("large");
      return this;
    }

    public Builder rounded(){
      addClassIfNotExists("rounded");
      return this;
    }

    public Builder prefWidth(double prefWidth) {
      this.prefWidth = prefWidth;
      return this;
    }

    public Builder prefHeight(double prefHeight) {
      this.prefHeight = prefHeight;
      return this;
    }

    public Builder style(String style) {
      this.style = style;
      return this;
    }

    public StyledButton build() {
      return new StyledButton(this);
    }
  }
}
