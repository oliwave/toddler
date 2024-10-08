package edu.bu.cs622.mode;

import edu.bu.cs622.path.Path;

public interface Mode {

  boolean validatePath(Path gamePath, Path userPath);

}
