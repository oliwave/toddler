package edu.bu.cs622.state;

public class Cell {
  private String name = "";
  private String color = "black";

  public Cell(String color) {
    setColor(color);
  }

  public Cell(String name, String color) {
    setName(name);
    setColor(color);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

}
