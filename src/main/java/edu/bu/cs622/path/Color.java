package edu.bu.cs622.path;

/**
 * This is the Color enum.
 * This class is responsible for representing a Color.
 */
public enum Color {
  BLUE("blue"),
  // Default color
  WHITE("white"),
  YELLOW("yellow");

  private final String color;

  /**
   * Create the Color object.
   * 
   * @param color the color
   */
  Color(String color) {
    this.color = color;
  }

  /**
   * The getter method for any given color.
   * 
   * @return Return Color's literal string
   */
  public String getColor() {
    return color;
  }

}
