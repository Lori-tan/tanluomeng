package com.example.triathlonova.game1.presenter.strategy;

import com.example.triathlonova.game.presenter.strategy.GameScore;
import com.example.triathlonova.game1.view.Game1View;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/** A score-tracker class for this game. */
public class Game1Score extends GameScore implements ObserverInterface {

  /** The view that use this score-tracker. */
  private Game1View game1View;

  /**
   * Initialize a tracking score device for this username and prepare the database to log data.
   * Watch for changes from activity to log those changes in reflect of score.
   *
   * @param myDB the database.
   * @param username input name by user.
   * @param game1View the game view that use this score-tracker.
   */
  public Game1Score(DBHelper myDB, String username, Game1View game1View) {
    super(myDB, username);
    setScore(Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnGame1Score())));
    this.game1View = game1View;
    game1View.addObserver(this);
  }

  /**
   * Update the game1 of this game by decreasing 10 every times until the game1 score is no higher
   * than 10. Store this update in the database and inform the activity to show it.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  @Override
  public void update(ObservableInterface o, Object arg) {
    if (getScore() > 10) {
      setScore(getScore() - 10);
    }
    getMyDB().updateData(getUsername(), DBHelper.getColumnGame1Score(), getScore());
    game1View.showScore();
  }
}
