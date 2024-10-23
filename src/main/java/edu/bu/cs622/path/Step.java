package edu.bu.cs622.path;

/**
 * This is the Step class.
 * This class is responsible for representing a Step.
 */
public class Step {
  private Location location;
  private Direction direction;
  private Color color = Color.WHITE;

  /**
   * Create the Step object with location, direction and color.
   * 
   * @param location  The location of the step
   * @param direction The direction of the step
   * @param color     The color of the step
   */
  public Step(Location location, Direction direction, Color color) {
    this.location = location;
    this.direction = direction;
    this.color = color;
  }

  /**
   * Create the Step object with location and direction.
   * 
   * @param location  The location of the step
   * @param direction The direction of the step
   */
  public Step(Location location, Direction direction) {
    this.location = location;
    this.direction = direction;
  }

  /**
   * The getter method for getting the direction of the step.
   * 
   * @return Return the direction of the step
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * The setter method for setting the direction of the step.
   * 
   * @param direction Assign the given direction to the step
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * The getter method for getting the color of the step.
   * 
   * @return Return the color of the step
   */
  public String getColor() {
    return color.getColor();
  }

  /**
   * The setter method for setting the color of the step.
   * 
   * @param color Assign the given color to the step
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * The getter method for getting the location of the step.
   * 
   * @return Return the location of the step
   */
  public Location getLocation() {
    return location;
  }

  /**
   * The setter method for setting the location of the step.
   * 
   * @param location Assign the given location to the step
   */
  public void setLocation(Location location) {
    this.location = location;
  }

}
