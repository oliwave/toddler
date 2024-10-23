package edu.bu.cs622.path;

public enum Color {
  BLUE("blue"),
  // Default color
  WHITE("white"),
  YELLOW("yellow");

  private final String color;

  Color(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }

}
