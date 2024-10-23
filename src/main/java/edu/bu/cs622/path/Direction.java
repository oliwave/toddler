package edu.bu.cs622.path;

/**
 * This is the Direction enum.
 * This class is responsible for representing a Direction.
 */
public enum Direction {
  UP("↑"),
  DOWN("↓"),
  LEFT("←"),
  RIGHT("→");

  private final String direction;

  /**
   * Create the Direction object.
   * 
   * @param direction the direction
   */
  Direction(String direction) {
    this.direction = direction;
  }

  /**
   * The getter method for any given direction.
   * 
   * @return Return Direction's literal string
   */
  public String getDirection() {
    return direction;
  }

}
