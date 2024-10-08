package edu.bu.cs622.view;

import java.util.ArrayList;
import java.util.List;

import edu.bu.cs622.path.Direction;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DirectionButtons implements Publisher {

  private static DirectionButtons directionButtons;

  private final HBox buttons = new HBox(10);

  private List<Subscriber> subscribers = new ArrayList<>();

  private Direction curDirection;

  public static DirectionButtons getDirectionButtons() {
    if (DirectionButtons.directionButtons == null) {
      directionButtons = new DirectionButtons();
    }

    return directionButtons;
  }

  private DirectionButtons() {
    // Buttons for selecting directions
    Button upButton = new Button(Direction.UP.getDirection());
    Button downButton = new Button(Direction.DOWN.getDirection());
    Button leftButton = new Button(Direction.LEFT.getDirection());
    Button rightButton = new Button(Direction.RIGHT.getDirection());
  
    upButton.setOnAction(e -> addMove(Direction.UP));
    downButton.setOnAction(e -> addMove(Direction.DOWN));
    leftButton.setOnAction(e -> addMove(Direction.LEFT));
    rightButton.setOnAction(e -> addMove(Direction.RIGHT));
  
    buttons.getChildren().addAll(leftButton, downButton, rightButton, upButton);
    buttons.setAlignment(Pos.CENTER);
  }

  private void addMove(Direction direction) {
    this.curDirection = direction;
    notifySubscribers();
  }

  public HBox getButtons() {
    return buttons;
  }

  public Direction getCurDirection() {
    return this.curDirection;
  }

  @Override
  public boolean subscribe(Subscriber s) {
    if (s != null) {
      this.subscribers.add(s);
      return true;
    }

    return false;
  }

  @Override
  public boolean unsubscribe(Subscriber s) {
    if (s != null) {
      return this.subscribers.remove(s);
    }

    return false;
  }

  @Override
  public void notifySubscribers() {
    for (Subscriber s : this.subscribers) {
      s.update(this);
    }
  }
}
