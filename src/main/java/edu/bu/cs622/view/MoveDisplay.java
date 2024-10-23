package edu.bu.cs622.view;

import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MoveDisplay implements Subscriber {
  private HBox box = new HBox(5);
  private DirectionButtons buttons = DirectionButtons.getDirectionButtons();
  private Submit launch = Submit.getSubmit();
  private static MoveDisplay moveDisplay;

  public static MoveDisplay getMoveDisplay() {
    if (moveDisplay == null) {
      moveDisplay = new MoveDisplay();
    }

    return moveDisplay;
  }

  private MoveDisplay() {
    this.buttons.subscribe(this);
    this.launch.subscribe(this);
    refresh();
  }

  private void addDirectionLabel(String direction) {
    Label moveLabel = new Label(direction);
    moveLabel.setStyle("-fx-border-color: red; -fx-padding: 5px;");
    box.getChildren().add(moveLabel);
  }

  private void addColorLabel(String color) {
    Label colorLabel = new Label();
    colorLabel
        .setStyle("-fx-pref-width: 30px; -fx-pref-height: 10px; -fx-border-color: red; -fx-background-color: " + color
            + "; -fx-padding: 5px;");
    box.getChildren().add(colorLabel);
  }

  public void refresh() {
    // Display of the selected moves
    box.getChildren().clear();
    box.setPadding(new Insets(10));
    box.setStyle("-fx-border-color: blue; -fx-border-width: 2px; -fx-padding: 5px;");
    box.setAlignment(Pos.CENTER);
  }

  public HBox getDisplayBox() {
    return this.box;
  }

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
