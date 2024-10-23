package edu.bu.cs622.state;

import edu.bu.cs622.path.Color;
import edu.bu.cs622.path.Direction;
import edu.bu.cs622.path.Location;
import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import edu.bu.cs622.view.DirectionButtons;
import edu.bu.cs622.view.Submit;
import java.util.List;

/**
 * This is the Game class.
 * This class is responsible for representing a Game.
 */
public class Player implements Subscriber {
  private static Player player;

  private Path path;
  private DirectionButtons buttons = DirectionButtons.getDirectionButtons();
  private Submit submit;
  private int locX;
  private int locY;

  /**
   * The static method that gets the Player object.
   * 
   * @param submit Submit object
   * @return Return the Player object
   */
  public static Player getPlaryer(Submit submit) {
    if (player == null) {
      player = new Player(submit);
    }

    return player;
  }

  /**
   * Create the Player object with the Submit object.
   * 
   * @param submit The Submit object
   */
  private Player(Submit submit) {
    this.submit = submit;
    this.buttons.subscribe(this);
    this.submit.subscribe(this);
    refresh();
  }

  /**
   * Only triggered when restarting or initializing the game.
   */
  private void refresh() {
    this.locX = 0;
    this.locY = 0;
    this.path = new Path();
  }

  /**
   * Add a step to the path when user clicks the direction buttons.
   */
  private void addStepToPath() {
    Direction d = buttons.getCurDirection();

    switch (d) {
      case UP:
        locY--;
        addStep(d, locX, locY);
        break;
      case DOWN:
        locY++;
        addStep(d, locX, locY);
        break;
      case LEFT:
        locX--;
        addStep(d, locX, locY);
        break;
      case RIGHT:
        locX++;
        addStep(d, locX, locY);
        break;
      default:
        break;
    }

    // System.out.println("Steps: " + path.getSteps().size() + " x: " + x + " y: " +
    // y);
  }

  /**
   * Set the Color of the step when user clicks the Color buttons.
   */
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

  /**
   * The helper method to create a Step with Location and Direction and then add the Step to path.
   * 
   * @param d The direction of the step
   * @param x The x of the Location
   * @param y The y of the Location
   */
  private void addStep(Direction d, int x, int y) {
    Location loc = new Location(x, y);
    path.addStep(new Step(loc, d));
  }

  public Path getPath() {
    return path;
  }

  /**
   * Triggered when color, direction or submit buttons are clicked.
   * 
   * @param publisher The publisher
   */
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
