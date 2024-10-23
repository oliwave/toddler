package edu.bu.cs622.state;

import java.util.List;

import edu.bu.cs622.path.Color;
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
        addStep(d, x, y);
        break;
      case DOWN:
        y++;
        addStep(d, x, y);
        break;
      case LEFT:
        x--;
        addStep(d, x, y);
        break;
      case RIGHT:
        x++;
        addStep(d, x, y);
        break;
    }

    // System.out.println("Steps: " + path.getSteps().size() + " x: " + x + " y: " + y);
  }

  private void setStepColor() {
    List<Step> steps = path.getSteps();
    // Only set the color to the step if steps are not empty
    if (!steps.isEmpty()) {
      Step lastStep = steps.get(steps.size() - 1);
      // Only set the color to the step if the last step is WHITE (default)
      if (lastStep.getColor().equals(Color.WHITE.getColor())) {
        lastStep.setColor(buttons.getCurColor());
      }
    }
  }

  private void addStep(Direction d, int x, int y) {
    Location loc = new Location(x, y);
    path.addStep(new Step(loc, d));
  }

  public Path getPath() {
    return path;
  }

  @Override
  public void update(Publisher publisher) {
    if (publisher instanceof DirectionButtons) {
      if (buttons.isColor()) {
        setStepColor();
      } else {
        addStepToPath();
      }
    } else if (publisher instanceof Submit) {
      refresh();
    }
  }

}
