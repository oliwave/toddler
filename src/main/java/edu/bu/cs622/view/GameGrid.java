package edu.bu.cs622.view;

import edu.bu.cs622.state.Game;
import edu.bu.cs622.state.Cell;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameGrid implements Subscriber {

  public static final int GRID_SIZE = 6; // Define a 6x6 grid

  private static GameGrid gameGrid;
  private GridPane gridPane;
  private Submit submit = Submit.getSubmit();
  private Game game = Game.getGame(submit);

  public static GameGrid getGameGrid() {
    if (gameGrid == null) {
      gameGrid = new GameGrid();
    }

    return gameGrid;
  }

  private GameGrid() {
    gridPane = new GridPane();
    this.submit.subscribe(this);
    refresh();
  }

  public void refresh() {
    gridPane.getChildren().clear();
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        Cell grid = game.getGridValue(i, j);
        Label cell = populateLabel(grid.getName(), grid.getColor());
        gridPane.add(cell, i, j);
      }
    }
  }

  private Label populateLabel(String text, String color) {
    Label cell = new Label(" " + text + " ");
    cell.setStyle(
        "-fx-pref-width: 100px; -fx-pref-height: 100px; -fx-border-color: black; -fx-alignment: center; -fx-background-color: "
            + color);
    return cell;
  }

  public GridPane getGridPane() {
    return gridPane;
  }

  @Override
  public void update(Publisher publisher) {
    refresh();
  }

}
