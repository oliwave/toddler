package edu.bu.cs622.view;

import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This is the MoveDisplay class.
 * This class is responsible for representing a MoveDisplay.
 */
public class MoveDisplay implements Subscriber {
  private HBox box = new HBox(5);
  private DirectionButtons buttons = DirectionButtons.getDirectionButtons();
  private Submit launch = Submit.getSubmit();
  private static MoveDisplay moveDisplay;

  /**
   * The static method that gets the MoveDisplay object.
   * 
   * @return Return a MoveDisplay object
   */
  public static MoveDisplay getMoveDisplay() {
    if (moveDisplay == null) {
      moveDisplay = new MoveDisplay();
    }

    return moveDisplay;
  }

  /*
   * Create a MoveDisplay object.
   */
  private MoveDisplay() {
    this.buttons.subscribe(this);
    this.launch.subscribe(this);
    refresh();
  }

  /**
   * Add a direction as a Label to the HBox.
   * 
   * @param direction The direction to add
   */
  private void addDirectionLabel(String direction) {
    Label moveLabel = new Label(direction);
    moveLabel.setStyle("-fx-border-color: red; -fx-padding: 5px;");
    box.getChildren().add(moveLabel);
  }

  /**
   * Add a Color as a Label to the HBox.
   * 
   * @param direction The Color to add
   */
  private void addColorLabel(String color) {
    Label colorLabel = new Label();
    String size = "-fx-pref-width: 30px; -fx-pref-height: 10px; ";
    colorLabel
        .setStyle(size + "-fx-border-color: red; -fx-background-color: " + color
            + "; -fx-padding: 5px;");
    box.getChildren().add(colorLabel);
  }

  /**
   * Remove all the Labels in the Hbox and reset to default after the user
   * submits.
   */
  public void refresh() {
    // Display of the selected moves
    box.getChildren().clear();
    box.setPadding(new Insets(10));
    box.setStyle("-fx-border-color: blue; -fx-border-width: 2px; -fx-padding: 5px;");
    box.setAlignment(Pos.CENTER);
  }

  /**
   * The Getter method for displaying directions and colors.
   * 
   * @return Return HBox containing all of the Labels, including directions and
   *         colors
   */
  public HBox getDisplayBox() {
    return this.box;
  }

  /**
   * Called when Submit or Direction buttons are clicked.
   * 
   * @param publisher The publisher passed in for identification
   */
  @Override
  public void update(Publisher publisher) {
    if (publisher instanceof DirectionButtons) {
      if (buttons.isColor()) {
        addColorLabel(this.buttons.getCurColor().getColor());
      } else {
        addDirectionLabel(this.buttons.getCurDirection().getDirection());
      }
    } else if (publisher instanceof Submit) {
      refresh();
    }
  }

}
