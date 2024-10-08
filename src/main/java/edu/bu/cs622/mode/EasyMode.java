package edu.bu.cs622.mode;

import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;

public class EasyMode implements Mode {

  @Override
  public boolean validatePath(Path gamePath, Path userPath) {

    for (Step step : gamePath.getSteps()) {
      System.out.println("gamePath -> Color: " + step.getColor() + " Direction: " + step.getDirection() + " Location: "
          + step.getLocation().getX() + "," + step.getLocation().getY());
    }

    for (Step step : userPath.getSteps()) {
      System.out.println("userPath -> Color: " + step.getColor() + " Direction: " + step.getDirection() + " Location: "
          + step.getLocation().getX() + "," + step.getLocation().getY());
    }

    return gamePath.getSteps().equals(userPath.getSteps());
  }

}
