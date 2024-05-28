import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.*;

public class homeController {
  @FXML
  Label gamesPlayedLabel;

  @FXML
  Label averageGuessesLabel;

  @FXML
  Button resetStatsButton;

  @FXML
  Button resetGameButton;

  @FXML
  Label guessesLabel;

  @FXML
  Label lettersRemainingLabel;

  @FXML
  Label lettersCorrectLabel;

  @FXML
  Label lettersIncorrectLabel;

  @FXML
  Label hintsLabel;

  @FXML
  TextField guessTextEntry;

  @FXML
  Button guessTextButton;

  public int numGuesses = 0;

  public int numGames = 0;
  public int numTotalGuesses = 0;

  public WordSet w;
  public homeController() {

    w = new WordSet(8);
  }
  
  @FXML
  protected void submitGuess() {
    String guess = guessTextEntry.getText();
    System.out.println("Guess submitted " + guess);
    guessTextEntry.setText("");
    
    w.submitGuess(guess);

    ArrayList<String> hints = w.guess;
    updateHints(hints);
    
    // Call getHints

    // Reset the letter stats
    // different game modes button changes word length
    // different files/words sets
    // Fix Average Guesses
    // Check single letters
    // optomization if we want to
    
    numGuesses++;
    updateGuesses();
    updateStats();

    guessesLabel.setText("Guesses: " + numGuesses);

    // Some check to see if the game is finished
    if (w.isComplete()) {
      numGames++;
      numTotalGuesses += numGuesses;
      resetGame();
    }
    
  }
  
  @FXML
  protected void resetStats() {
    numTotalGuesses = 0;
    numGames = 0;
    updateStats();
  }

  @FXML
  protected void resetGame() {
    w = new WordSet(8);
    numGuesses = 0;
    updateGuesses();
    updateHints(w.guess);
    updateStats();
    // reset game
  }

  public void updateStats() {
    // Update Games Played
    gamesPlayedLabel.setText("Games Played: " + numGames);
    averageGuessesLabel.setText("Average Guesses: " + ((double) numTotalGuesses / numGames));

    lettersRemainingLabel.setText("Letters Remaining: " + w.possibleGuesses);
    lettersCorrectLabel.setText("Letters Correct: " + w.successfulGuesses);
    lettersIncorrectLabel.setText("Letters Incorrect: " + w.unsuccessfulGuesses);
  }

  public void updateHints(ArrayList<String> hints) {
    String res = "";
    for (String s : hints) {
      res += s + " ";
    }
    hintsLabel.setText(res);
  }

  public void updateGuesses() {
    guessesLabel.setText("Guesses: " + numGuesses);
  }
}