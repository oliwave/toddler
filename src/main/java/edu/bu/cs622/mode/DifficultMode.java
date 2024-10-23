package edu.bu.cs622.mode;

import java.util.List;

import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;

public class DifficultMode implements Mode {

  @Override
  public boolean validatePath(Path gamePath, Path userPath) {
    List<Step> gameSteps = gamePath.getSteps();
    List<Step> userSteps = userPath.getSteps();

    for (int i = 0; i < gameSteps.size(); i++) {
      if (!gameSteps.get(i).getDirection().equals(userSteps.get(i).getDirection())) {
        return false;
      }
      if (!gameSteps.get(i).getColor().equals(userSteps.get(i).getColor())) {
        return false;
      }
    }

    return true;
  }

}
