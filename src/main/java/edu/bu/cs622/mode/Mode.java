package edu.bu.cs622.mode;

import edu.bu.cs622.path.Path;

/**
 * This is the Mode interface.
 * This interface is responsible for representing a Mode.
 */
public interface Mode {

  /**
   * Validate the user-selected path against the game-generated one based on
   * the different modes of the game.
   * 
   * @param gamePath The random path generated by the game
   * @param userPath The user input path
   * @return Return true if the user input path is the same as the game generated
   */
  boolean validatePath(Path gamePath, Path userPath);

}
