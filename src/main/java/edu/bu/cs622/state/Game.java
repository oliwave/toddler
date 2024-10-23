package edu.bu.cs622.state;

import edu.bu.cs622.path.Color;
import edu.bu.cs622.path.Direction;
import edu.bu.cs622.path.Location;
import edu.bu.cs622.path.Path;
import edu.bu.cs622.path.Step;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import edu.bu.cs622.view.GameGrid;
import edu.bu.cs622.view.Submit;
import java.util.List;
import java.util.Random;

/**
 * This is the Game class.
 * This class is responsible for representing a Game.
 */
public class Game implements Subscriber {

  public static final int GRID_SIZE = GameGrid.GRID_SIZE; // Define a 6x6 grid

  private static Game game;

  private static final String FINAL_SPOT = "Final";
  private static final String AGENT = "Player";

  private Cell[][] grid;
  private int agentX;
  private int agentY;
  private int finalX;
  private int finalY;
  private Path path;

  private Submit submit;

  /**
   * The static method to get the Game object.
   * 
   * @param submit Submit object
   * @return Return the Game object
   */
  public static Game getGame(Submit submit) {
    if (game == null) {
      game = new Game(submit);
    }

    return game;
  }

  /**
   * Create the Game object with the Submit object.
   * 
   * @param submit Submit object
   */
  private Game(Submit submit) {
    this.submit = submit;
    this.submit.subscribe(this);
    this.init();
  }

  /**
   * Trigger for the bootstrap or the restart of the game.
   */
  public void init() {
    initializeGrid();
    placeAgent();
    placeFinalSpot();
    generateRandomPath();
    displayPathOnGrid();
  }

  /**
   * Initialize the grid with empty spaces.
   */
  private void initializeGrid() {
    grid = new Cell[GRID_SIZE][GRID_SIZE];
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        grid[i][j] = new Cell("gray");
      }
    }
  }

  /**
   * Place the agent at the start (0, 0).
   */
  private void placeAgent() {
    agentX = 0;
    agentY = 0;
    grid[agentX][agentY] = new Cell(AGENT, "white");
  }

  /**
   * Place the final spot at the end (5, 5).
   */
  private void placeFinalSpot() {
    finalX = GRID_SIZE - 1;
    finalY = GRID_SIZE - 1;
    grid[finalX][finalY] = new Cell(FINAL_SPOT, "white");
  }

  /**
   * Generate a random path that ends either above or to the left of (5,5).
   */
  private void generateRandomPath() {
    path = new Path();
    int x = 0;
    int y = 0;

    Random rand = new Random();

    // Keep moving either right or down until we get close to the final spot
    while (x < GRID_SIZE - 2 || y < GRID_SIZE - 2) {
      Color color = rand.nextBoolean() ? Color.YELLOW : Color.BLUE;
      if (x == GRID_SIZE - 2) {
        y++;
        addStep(Direction.DOWN, color, x, y);
      } else if (y == GRID_SIZE - 2) {
        x++;
        addStep(Direction.RIGHT, color, x, y);
      } else {
        if (rand.nextBoolean()) {
          x++;
          addStep(Direction.RIGHT, color, x, y);
        } else {
          y++;
          addStep(Direction.DOWN, color, x, y);
        }
      }
    }

    Color color = rand.nextBoolean() ? Color.YELLOW : Color.BLUE;

    // Make sure the final step is either above or to the left of (5,5)
    if (rand.nextBoolean()) {
      addStep(Direction.RIGHT, color, x + 1, y);
    } else {
      addStep(Direction.DOWN, color, x, y + 1);
    }
  }

  /**
   * Display the path on the grid using "X".
   */
  private void displayPathOnGrid() {
    for (Step step : path.getSteps()) {
      int x = step.getLocation().getLocX();
      int y = step.getLocation().getLocY();
      grid[x][y] = new Cell(step.getColor());
    }
  }

  /**
   * Get the specific Cell object given the x and y position.
   * 
   * @param x The x of the grid
   * @param y The y of the grid
   * @return Return the Cell object
   */
  public Cell getGridValue(int x, int y) {
    return this.grid[x][y];
  }

  /**
   * The getter method for getting the list of Steps.
   * 
   * @return Return the list of Steps
   */
  public List<Step> getSteps() {
    return path.getSteps();
  }
  
  /**
   * The getter method for getting the Path object.
   * 
   * @return Return the Path object
   */
  public Path getPath() {
    return path;
  }

  /**
   * Add a step with direction, color, x, and y information to the Path.
   * 
   * @param d The direction of the step
   * @param color The color of the step
   * @param x The x of the Location
   * @param y The y of the Location
   */
  private void addStep(Direction d, Color color, int x, int y) {
    Location loc = new Location(x, y);
    path.addStep(new Step(loc, d, color));
  }

  /**
   * Invoke when the game is either boostraping or restarting.
   * 
   * @param publisher The publisher
   */
  @Override
  public void update(Publisher publisher) {
    init();
  }
}
