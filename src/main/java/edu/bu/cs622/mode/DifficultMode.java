package edu.bu.cs622.mode;

import edu.bu.cs622.path.Path;

public class DifficultMode implements Mode {

  @Override
  public boolean validatePath(Path gamePath, Path userPath) {
    return true;
  }

}
