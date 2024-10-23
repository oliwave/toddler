package edu.bu.cs622.state;

/**
 * This is the Cell class.
 * This class is responsible for representing a Cell.
 */
public class Cell {
  private String name = "";
  private String color = "black";

  /**
   * Creat the Cell object with the background color.
   * 
   * @param color The background color for the Cell
   */
  public Cell(String color) {
    setColor(color);
  }

  /**
   * Creat the Cell object with the background color and name.
   * 
   * @param name  The name for the Cell
   * @param color The background color for the Cell
   */
  public Cell(String name, String color) {
    setName(name);
    setColor(color);
  }

  /**
   * The getter method for getting the Cell name.
   * 
   * @return Return the Cell name
   */
  public String getName() {
    return name;
  }

  /**
   * The setter method for setting the name of the Cell.
   * 
   * @param name Set the name of the Cell
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The getter method for getting the Cell color.
   * 
   * @return Return the Cell color
   */
  public String getColor() {
    return color;
  }

  /**
   * The setter method for setting the color of the Cell.
   * 
   * @param color Set the color of the Cell
   */
  public void setColor(String color) {
    this.color = color;
  }

}
