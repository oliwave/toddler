package edu.bu.cs622.state;

import java.util.List;
import java.util.Random;

import edu.bu.cs622.path.Direction;
import edu.bu.cs622.path.Location;
import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import edu.bu.cs622.view.GameGrid;
import edu.bu.cs622.view.Submit;

public class Game implements Subscriber {

  public static final int GRID_SIZE = GameGrid.GRID_SIZE; // Define a 6x6 grid

  private static Game game;

  private static final String FINAL_SPOT = "F";
  private static final String AGENT = "P";

  private Cell[][] grid;
  private int agentX, agentY;
  private int finalX, finalY;
  private Path path;

  private Submit submit;

  public static Game getGame(Submit submit) {
    if (game == null) {
      game = new Game(submit);
    }

    return game;
  }

  private Game(Submit submit) {
    this.submit = submit;
    this.submit.subscribe(this);
    this.init();
  }

  public void init() {
    initializeGrid();
    placeAgent();
    placeFinalSpot();
    generateRandomPath();
    displayPathOnGrid();
  }

  // Initialize the grid with empty spaces
  private void initializeGrid() {
    grid = new Cell[GRID_SIZE][GRID_SIZE];
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        grid[i][j] = new Cell("-", "white");
      }
    }
  }

  // Place the agent at the start (0, 0)
  private void placeAgent() {
    agentX = 0;
    agentY = 0;
    grid[agentX][agentY] = new Cell(AGENT, "white");
  }

  // Place the final spot at the end (5, 5)
  private void placeFinalSpot() {
    finalX = GRID_SIZE - 1;
    finalY = GRID_SIZE - 1;
    grid[finalX][finalY] = new Cell(FINAL_SPOT, "white");
  }

  // Generate a random path that ends either above or to the left of (5,5)
  private void generateRandomPath() {
    path = new Path();
    int x = 0, y = 0;

    // GameController.setGamePath(path);

    Random rand = new Random();

    // Keep moving either right or down until we get close to the final spot
    while (x < GRID_SIZE - 2 || y < GRID_SIZE - 2) {
      String color = rand.nextBoolean() ? "yellow" : "blue";
      if (x == GRID_SIZE - 2) {
        y++;
        addStep(Direction.RIGHT, color, x, y);
      } else if (y == GRID_SIZE - 2) {
        x++;
        addStep(Direction.DOWN, color, x, y);
      } else {
        if (rand.nextBoolean()) {
          x++;
          addStep(Direction.DOWN, color, x, y);
        } else {
          y++;
          addStep(Direction.RIGHT, color, x, y);
        }
      }
    }

    String color = rand.nextBoolean() ? "yellow" : "blue";

    // Make sure the final step is either above or to the left of (5,5)
    if (rand.nextBoolean()) {
      addStep(Direction.RIGHT, color, x + 1, y);
    } else {
      addStep(Direction.DOWN, color, x, y + 1);
    }
  }

  // Display the path on the grid using "X"
  private void displayPathOnGrid() {
    for (Step step : path.getSteps()) {
      int x = step.getLocation().getX();
      int y = step.getLocation().getY();
      grid[x][y] = new Cell("X", step.getColor());
    }
  }

  public Cell getGridValue(int i, int j) {
    return this.grid[i][j];
  }

  public List<Step> getSteps() {
    return path.getSteps();
  }

  public Path getPath() {
    return path;
  }

  private void addStep(Direction d, String color, int x, int y) {
    Location loc = new Location(x, y);
    path.addStep(new Step(loc, d, color));
  }

  @Override
  public void update(Publisher publisher) {
    init();
  }
}
