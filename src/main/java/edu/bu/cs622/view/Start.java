package edu.bu.cs622.view;

import javafx.scene.control.Button;

/**
 * This is the start class.
 * This class is responsible for representing a start.
 */
public class Start {
  private Button startButton = new Button("🚀");
  private DirectionButtons directionButtons = DirectionButtons.getDirectionButtons();
  private static Start start;

  /**
   * Create a start object.
   */
  private Start() {
    startButton.setOnAction(e -> start());
  }

  /**
   * The static method to get the start object.
   * 
   * @return A start object
   */
  public static Start getStart() {
    if (start == null) {
      start = new Start();
    }

    return start;
  }

  /**
   * Getter method for start button.
   * 
   * @return A sumit button
   */
  public Button getStartButton() {
    return startButton;
  }

  /**
   * start the results and see if userr win the game.
   * 
   * @param m Easy mode or hard mode
   */
  private void start() {
    directionButtons.getButtons().setVisible(true);
  }

}
