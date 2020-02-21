package com.example.triathlonova.game.presenter.processor;

import android.graphics.Canvas;

/** A manager of this Game that deals with update elements in the game. */
public abstract class GameManager {

  /** The width of this display. */
  private int width;
  /** The height of this display. */
  private int height;

  /**
   * Set the value of the variables to the corresponding parameters.
   *
   * @param width a passed-in int value.
   * @param height a passed-in int value.
   */
  public GameManager(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Return the width of a row of locations.
   *
   * @return the value of width variable.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Reset the variable to the value of this parameter.
   *
   * @param width a passed-in int value.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Return the height of a row of locations.
   *
   * @return the value of height variable.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Make the elements in game drawManager themselves.
   *
   * @param canvas the host to each drawManager calls onto the display.
   */
  public abstract void drawManager(Canvas canvas);

  /**
   * Make the character in this game to take its turn and give an action according to the specific
   * functionality.
   */
  public abstract void updateManager();
}
