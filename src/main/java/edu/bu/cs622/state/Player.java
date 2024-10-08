package edu.bu.cs622.state;

import edu.bu.cs622.path.Direction;
import edu.bu.cs622.path.Location;
import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import edu.bu.cs622.view.DirectionButtons;
import edu.bu.cs622.view.Submit;

public class Player implements Subscriber {
  private static Player player;

  private Path path;
  private DirectionButtons buttons = DirectionButtons.getDirectionButtons();
  private Submit submit;
  private int x;
  private int y;

  public static Player getPlaryer(Submit submit) {
    if (player == null) {
      player = new Player(submit);
    }

    return player;
  }

  private Player(Submit submit) {
    this.submit = submit;
    this.buttons.subscribe(this);
    this.submit.subscribe(this);
    refresh();
  }
  
  private void refresh() {
    this.x = 0;
    this.y = 0;
    this.path = new Path();
  }

  // Implement Color
  private void addStepToPath() {
    Direction d = buttons.getCurDirection();

    switch (d) {
      case UP:
        y--;
        addStep(d, "white", x, y);
        break;
      case DOWN:
        y++;
        addStep(d, "white", x, y);
        break;
      case LEFT:
        x--;
        addStep(d, "white", x, y);
        break;
      case RIGHT:
        x++;
        addStep(d, "white", x, y);
        break;
    }

    System.out.println("Steps: " + path.getSteps().size() + " x: " + x + " y: " + y);
  }

  private void addStep(Direction d, String color, int x, int y) {
    Location loc = new Location(x, y);
    path.addStep(new Step(loc, d, color));
  }

  public Path getPath() {
    return path;
  }

  @Override
  public void update(Publisher publisher) {
    if (publisher instanceof DirectionButtons) {
      addStepToPath();
    } else if (publisher instanceof Submit) {
      refresh();
    }
  }

}
