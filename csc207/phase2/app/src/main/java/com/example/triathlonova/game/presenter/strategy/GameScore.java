package com.example.triathlonova.game.presenter.strategy;

import com.example.triathlonova.helper.database.DBHelper;

/** A score-tracker class for this game. */
public abstract class GameScore {

  /** A score. */
  private int score;
  /** The database helper. */
  private DBHelper myDB;
  /** The user's name. */
  private String username;

  /**
   * Initialize a tracking score device for this username and prepare the database to log data.
   *
   * @param myDB the database.
   * @param username input name by user.
   */
  public GameScore(DBHelper myDB, String username) {
    this.myDB = myDB;
    this.username = username;
  }

  /**
   * Return current score of this user.
   *
   * @return the score variable.
   */
  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public DBHelper getMyDB() {
    return myDB;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
