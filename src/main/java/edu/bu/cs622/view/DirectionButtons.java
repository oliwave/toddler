package edu.bu.cs622.view;

import edu.bu.cs622.path.Color;
import edu.bu.cs622.path.Direction;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * This is the DirectionButtons class.
 * This class is responsible for representing a DirectionButtons.
 */
public class DirectionButtons implements Publisher {

  private static DirectionButtons directionButtons;

  private final HBox buttons = new HBox(10);

  private List<Subscriber> subscribers = new ArrayList<>();

  private Direction curDirection;
  private Color curColor;
  private Boolean isColor;

  /**
   * The static method that gets the DirectionButtons object.
   * 
   * @return A DirectionButtons object
   */
  public static DirectionButtons getDirectionButtons() {
    if (DirectionButtons.directionButtons == null) {
      directionButtons = new DirectionButtons();
    }

    return directionButtons;
  }

  /**
   * Create the DirectionButtons object and initialize all the buttons.
   */
  private DirectionButtons() {
    // Buttons for selecting directions
    Button upButton = new Button(Direction.UP.getDirection());
    upButton.setOnAction(e -> addMove(Direction.UP));

    Button downButton = new Button(Direction.DOWN.getDirection());
    downButton.setOnAction(e -> addMove(Direction.DOWN));

    Button leftButton = new Button(Direction.LEFT.getDirection());
    leftButton.setOnAction(e -> addMove(Direction.LEFT));

    Button rightButton = new Button(Direction.RIGHT.getDirection());
    rightButton.setOnAction(e -> addMove(Direction.RIGHT));

    String borderColor = "-fx-border-color: black; ";

    Button blueButton = new Button();
    blueButton.setPrefHeight(10);
    blueButton.setPrefWidth(30);
    blueButton.setStyle(borderColor + "-fx-background-color: " + Color.BLUE.getColor());
    blueButton.setOnAction(e -> addColor(Color.BLUE));

    Button yellowButton = new Button();
    yellowButton.setPrefHeight(10);
    yellowButton.setPrefWidth(30);
    yellowButton.setStyle(borderColor + "-fx-background-color: " + Color.YELLOW.getColor());
    yellowButton.setOnAction(e -> addColor(Color.YELLOW));

    buttons.getChildren().addAll(
        leftButton,
        downButton,
        rightButton,
        upButton,
        blueButton,
        yellowButton);
    buttons.setAlignment(Pos.CENTER);
    buttons.setVisible(false);
  }

  /**
   * Set the current Color to the one the user clicks.
   * 
   * @param color The color that the user clicks
   */
  private void addColor(Color color) {
    this.curColor = color;
    this.isColor = true;
    notifySubscribers();
  }

  /**
   * Set the current Direction to the one the user clicks.
   * 
   * @param color The direction that the user clicks
   */
  private void addMove(Direction direction) {
    this.curDirection = direction;
    this.isColor = false;
    notifySubscribers();
  }

  /**
   * The getter method for getting the buttons.
   * 
   * @return Return the HBox containing all the buttons
   */
  public HBox getButtons() {
    return buttons;
  }

  /**
   * The getter method for getting the current direction.
   * 
   * @return The current direction
   */
  public Direction getCurDirection() {
    return this.curDirection;
  }

  /**
   * The getter method for getting the current color.
   * 
   * @return The current color
   */
  public Color getCurColor() {
    return this.curColor;
  }

  /**
   * The getter method to see if isColor is true.
   * 
   * @return Return true if the latest operation from the user is clicking the
   *         color button
   */
  public Boolean isColor() {
    return isColor;
  }

  /**
   * Subscribe the button-clicked events.
   * 
   * @param s The subscriber
   * @return Return true if subscription to the button-clicked event successfully
   */
  @Override
  public boolean subscribe(Subscriber s) {
    if (s != null) {
      this.subscribers.add(s);
      return true;
    }

    return false;
  }

  /**
   * Unsubscribe the button-clicked events.
   * 
   * @param s The subscriber
   * @return Return true if subscription to the button-clicked event is removed
   *         successfully
   */
  @Override
  public boolean unsubscribe(Subscriber s) {
    if (s != null) {
      return this.subscribers.remove(s);
    }

    return false;
  }

  /**
   * Notify all subscribers when a button is clicked.
   */
  @Override
  public void notifySubscribers() {
    for (Subscriber s : this.subscribers) {
      s.update(this);
    }
  }
}
