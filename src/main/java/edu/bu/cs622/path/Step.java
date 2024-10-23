package edu.bu.cs622.path;

public class Step {
  private Location location;
  private Direction direction;
  private Color color = Color.WHITE;

  public Step(Location location, Direction direction, Color color) {
    this.location = location;
    this.direction = direction;
    this.color = color;
  }

  public Step(Location location, Direction direction) {
    this.location = location;
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public String getColor() {
    return color.getColor();
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}