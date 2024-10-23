package edu.bu.cs622.view;

import edu.bu.cs622.state.Cell;
import edu.bu.cs622.state.Game;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This is the GameGrid class.
 * This class is responsible for representing a GameGrid.
 */
public class GameGrid implements Subscriber {

  public static final int GRID_SIZE = 6; // Define a 6x6 grid

  private static GameGrid gameGrid;
  private GridPane gridPane;
  private Submit submit = Submit.getSubmit();
  private Game game = Game.getGame(submit);

  /**
   * The static method that gets the GameGrid Object.
   * 
   * @return A GameGrid object
   */
  public static GameGrid getGameGrid() {
    if (gameGrid == null) {
      gameGrid = new GameGrid();
    }

    return gameGrid;
  }

  /*
   * Create the GameGrid object.
   */
  private GameGrid() {
    gridPane = new GridPane();
    this.submit.subscribe(this);
    refresh();
  }

  /**
   * Reset all the cells in the grid to the initial state.
   */
  public void refresh() {
    gridPane.getChildren().clear();
    for (int x = 0; x < GRID_SIZE; x++) {
      for (int y = 0; y < GRID_SIZE; y++) {
        Cell grid = game.getGridValue(x, y);
        Label cell = populateLabel(grid.getName(), grid.getColor());
        gridPane.add(cell, x, y);
      }
    }
  }

  /**
   * A simple method to create labels.
   * 
   * @param text  The text for the Label
   * @param color The background color for the Label
   * @return Return the Label
   */
  private Label populateLabel(String text, String color) {
    String size = "-fx-pref-width: 100px; -fx-pref-height: 100px; ";
    String borderColor = "-fx-border-color: black; ";
    Label cell = new Label(" " + text + " ");
    cell.setStyle(size + borderColor + "-fx-alignment: center; -fx-background-color: " + color);
    return cell;
  }

  /**
   * The getter method that returns the GridPane object.
   * 
   * @return Return the gridPane
   */
  public GridPane getGridPane() {
    return gridPane;
  }

  /**
   * Trigger the refresh method when restarting the game.
   */
  @Override
  public void update(Publisher publisher) {
    refresh();
  }

}
