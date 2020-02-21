package com.example.triathlonova.game3.presenter.strategy;

import com.example.triathlonova.game.presenter.strategy.GameScore;
import com.example.triathlonova.game3.view.Game3View;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/** A score-tracker class for game3. */
public class Game3Score extends GameScore implements ObserverInterface {

  /** The view that use this score-tracker. */
  private Game3View view;
  /** The rate of the score. */
  private double rate;

  /**
   * Initialize a tracking score device for this username and prepare the database to log data.
   * Watch for changes from activity to log those changes in reflect of score.
   *
   * @param myDB the database.
   * @param username input name by user.
   * @param level the level of the game.
   * @param view the game view that use this score-tracker.
   */
  public Game3Score(DBHelper myDB, String username, String level, Game3View view) {
    super(myDB, username);
    setScore(Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnGame3Score())));
    this.view = view;
    setRate(level);
    view.addObserver(this);
  }

  /**
   * Set the rate of the score for different levels.
   *
   * @param level the level of the game.
   */
  private void setRate(String level) {
    switch (level) {
      case "1":
        rate = 1;
        break;
      case "2":
        rate = 1.2;
        break;
      case "3":
        rate = 1.5;
        break;
    }
  }

  /**
   * Update score of game3 by adding 10 every times a sheep is pushed into the sewer. Store this
   * update in the database and inform the activity to show it.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  @Override
  public void update(ObservableInterface o, Object arg) {
    setScore(getScore() + (int) (10 * rate));
    getMyDB().updateData(getUsername(), DBHelper.getColumnGame1Score(), getScore());
    view.showScore();
  }
}
