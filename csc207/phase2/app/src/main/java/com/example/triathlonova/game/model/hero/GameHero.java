package com.example.triathlonova.game.model.hero;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.game.model.item.GameCollideItem;

/** A hero of the game. */
public abstract class GameHero extends GameCollideItem {

  /** The number of lives this hero still has. */
  private int livesCount;

  /** Constructs a new hero. */
  protected GameHero(int x, int y, Rect rect) {
    super(x, y, rect);
  }

  /**
   * Return how many life this hero has left.
   *
   * @return an int of the amount of life of this hero.
   */
  public int getLivesCount() {
    return livesCount;
  }

  /**
   * set amount of lives of this hero to livesCount.
   *
   * @param livesCount amount of lives of this hero.
   */
  public void setLivesCount(int livesCount) {
    this.livesCount = livesCount;
  }

  /**
   * Add one more life to the hero if the string is a "+" or take away one life of the hero if the
   * string is a "-".
   *
   * @param s a string of either "+" or "-".
   */
  public void logLives(String s) {
    if (s.equals("+")) {
      addLife();
    } else {
      loseLife();
      newLife();
    }
  }

  /**
   * Let the hero return to the first beginning phase of the game and record that it has lost one
   * life.
   */
  protected abstract void newLife();

  /** Add one life to the hero by incrementing livesCount by 1. */
  private void addLife() {
    livesCount++;
  }

  /** Take away one life of the hero by decrementing livesCount by 1. */
  private void loseLife() {
    livesCount--;
  }

  /**
   * Return whether this is the hero's last life in this game.
   *
   * @return true iff the livesCount variable has value 1.
   */
  public boolean lastLife() {
    return getLivesCount() == 1;
  }

  /**
   * Draw this hero.
   *
   * @param canvas the host on which to this draw call.
   */
  @Override
  public abstract void draw(Canvas canvas);

  /** Update the hero, by design and action of the user. */
  public abstract void update();
}
