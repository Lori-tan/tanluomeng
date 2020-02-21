package com.example.triathlonova.game2.presenter.strategy;

import com.example.triathlonova.game.presenter.strategy.GameScore;
import com.example.triathlonova.game2.view.Game2View;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/**
 * Game2Score is an ObserverInterface with corresponding Observer(Game2MainActivity). When the
 * Observer sends a signal indicating the player has gained score, this observer interface will
 * react by retrieving score from the database, adding 10 to the score and storing it back to the
 * database
 */
public class Game2Score extends GameScore implements ObserverInterface {

  /** The view that use this score-tracker. */
  private Game2View game2View;

  /**
   * Initialize a tracking score device for this username and prepare the database to log data.
   * Watch for changes from activity to log those changes in reflect of score.
   *
   * @param myDB the database.
   * @param username input name by user.
   * @param game2View the game view that use this score-tracker.
   */
  public Game2Score(DBHelper myDB, String username, Game2View game2View) {
    super(myDB, username);
    setScore(Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnGame2Score())));
    this.game2View = game2View;
    game2View.addObserver(this);
  }

  /**
   * Update the game2 of this game by increasing 10 on every time the hero touches the gem. Store
   * this update in the database and inform the activity to show it.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  @Override
  public void update(ObservableInterface o, Object arg) {
    setScore(getScore() + 10);
    getMyDB().updateData(getUsername(), DBHelper.getColumnGame2Score(), getScore());
    game2View.showScore();
  }
}
