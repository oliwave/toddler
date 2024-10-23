package edu.bu.cs622.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.bu.cs622.mode.DifficultMode;
import edu.bu.cs622.mode.EasyMode;
import edu.bu.cs622.mode.Mode;
import edu.bu.cs622.state.Game;
import edu.bu.cs622.state.GameController;
import edu.bu.cs622.state.Player;
import edu.bu.cs622.utils.observer.Publisher;
import edu.bu.cs622.utils.observer.Subscriber;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Submit implements Publisher {
  private Button submitEasyButton = new Button("Easy");
  private Button submitHardButton = new Button("Hard");
  private List<Subscriber> subscribers = new ArrayList<>();
  private GameController gc;
  private Player player = Player.getPlaryer(this);
  private Game game = Game.getGame(this);
  private static Submit submit;

  public static Submit getSubmit() {
    if (submit == null) {
      submit = new Submit();
    }

    return submit;
  }

  private Submit() {
    submitEasyButton.setOnAction(e -> submit(new EasyMode()));
    submitHardButton.setOnAction(e -> submit(new DifficultMode()));
  }

  public Button getSubmitHardButton() {
    return submitHardButton;
  }

  public Button getSubmitEasyButton() {
    return submitEasyButton;
  }

  private void submit(Mode m) {
    gc = new GameController(m);

    if (gc.validatePath(game.getPath(), player.getPath())) {
      showAlert("Success", "Congratulations! You've followed the correct path and won the game.", true);
    } else {
      showAlert("Failure", "Oops! The path you entered is incorrect. Better luck next time!", true);
    }

    notifySubscribers();
  }

  // Show an alert box for game feedback and restart option
  private void showAlert(String title, String message, boolean restartOption) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message + "\nWould you like to play again?");

    ButtonType yesButton = new ButtonType("Yes");
    ButtonType noButton = new ButtonType("No");
    alert.getButtonTypes().setAll(yesButton, noButton);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == noButton) {
      System.exit(0);
    }
  }

  @Override
  public boolean subscribe(Subscriber s) {
    if (s != null) {
      this.subscribers.add(s);
      return true;
    }

    return false;
  }

  @Override
  public boolean unsubscribe(Subscriber s) {
    if (s != null) {
      return this.subscribers.remove(s);
    }

    return false;
  }

  @Override
  public void notifySubscribers() {
    for (Subscriber s : this.subscribers) {
      s.update(this);
    }
  }

}
