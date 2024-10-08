package edu.bu.cs622.path;

public enum Direction {
  UP("\u2191"),
  DOWN("\u2193"),
  LEFT("\u2190"),
  RIGHT("\u2192");

  private final String direction;

  Direction(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }

}
