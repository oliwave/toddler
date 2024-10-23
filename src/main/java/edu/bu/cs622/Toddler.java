package edu.bu.cs622;

import edu.bu.cs622.view.DirectionButtons;
import edu.bu.cs622.view.GameGrid;
import edu.bu.cs622.view.Submit;
import edu.bu.cs622.view.MoveDisplay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Toddler extends Application {

    private GameGrid gameGrid = GameGrid.getGameGrid();
    private DirectionButtons directionButtons = DirectionButtons.getDirectionButtons();
    private MoveDisplay moveDisplay = MoveDisplay.getMoveDisplay();
    private Submit submit = Submit.getSubmit();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Toddler Memory Game");

        // Center the grid in the layout
        VBox gridContainer = new VBox(gameGrid.getGridPane());

        VBox vbox = new VBox(20, gridContainer, directionButtons.getButtons(), moveDisplay.getDisplayBox(),
                submit.getSubmitEasyButton(), submit.getSubmitHardButton());
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
