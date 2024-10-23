package edu.bu.cs622.mode;

import java.util.List;

import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;

public class EasyMode implements Mode {

  @Override
  public boolean validatePath(Path gamePath, Path userPath) {

    List<Step> gameSteps = gamePath.getSteps();
    List<Step> userSteps = userPath.getSteps();

    // for (Step step : gameSteps) {
    // System.out.println("gamePath -> Color: " + step.getColor() + " Direction: " +
    // step.getDirection() + " Location: "
    // + step.getLocation().getX() + "," + step.getLocation().getY());
    // }

    // for (Step step : userSteps) {
    // System.out.println("userPath -> Color: " + step.getColor() + " Direction: " +
    // step.getDirection() + " Location: "
    // + step.getLocation().getX() + "," + step.getLocation().getY());
    // }

    for (int i = 0; i < gameSteps.size(); i++) {
      if (!gameSteps.get(i).getDirection().equals(userSteps.get(i).getDirection())) {
        return false;
      }
    }

    return true;
  }

}
