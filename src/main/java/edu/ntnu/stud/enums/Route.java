package edu.ntnu.stud.enums;

public enum Route {
  HOME;

  @Override
  public String toString() {
    String name = this.name().toLowerCase().replace("_", " ");
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }
}
