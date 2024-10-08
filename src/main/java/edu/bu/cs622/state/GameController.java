package edu.bu.cs622.state;

import edu.bu.cs622.mode.Mode;
import edu.bu.cs622.path.Path;

/*
 * TODO: Delegation pattern
 * https://java-design-patterns.com/patterns/delegation/#programmatic-example-of-delegation-pattern-in-java
 * 
 */
public class GameController implements Mode {

  private final Mode mode;

  public GameController(Mode mode) {
    this.mode = mode;
  }

  @Override
  public boolean validatePath(Path gamePath, Path userPath) {
    if (gamePath == null || userPath == null) {
      System.out.println("game or user path is null");
      return false;
    }
    return mode.validatePath(gamePath, userPath);
  }

}
