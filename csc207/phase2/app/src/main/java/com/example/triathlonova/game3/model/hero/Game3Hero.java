package com.example.triathlonova.game3.model.hero;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.game.model.hero.GameHero;
import com.example.triathlonova.game3.model.item.Sewer;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;
import com.example.triathlonova.game3.view.Game3View;

/** An player of the push sheep game. This is an abstract class. */
public abstract class Game3Hero extends GameHero {

  /** The push sheep manager that this player is in */
  private Game3Manager game3Manager;

  /**
   * Constructs a new player.
   *
   * @param rec the rectangle that represent this player.
   * @param manager the push sheep manager that this player is in.
   */
  Game3Hero(Rect rec, Game3Manager manager) {
    // x, y refers to the top left coordinate
    super(rec.left / Game3View.getScale(), rec.top / Game3View.getScale(), rec);
    game3Manager = manager;
  }

  /** Get the game3Manager for further usage. */
  Game3Manager getGame3Manager() {
    return game3Manager;
  }

  /** The player moves up. */
  public void goUp() {
    if (getY() > 0) {
      setY(getY() - 1);
    }
  }

  /** The player moves down. */
  public void goDown() {
    // the control bar of the screen takes up some space
    if (getY() < (game3Manager.getHeight() - 3)) {
      setY(getY() + 1);
    }
  }

  /** The player moves left. */
  public void goLeft() {
    if (getX() > 0) {
      setX(getX() - 1);
    }
  }

  /** The player moves right. */
  public void goRight() {
    if (getX() < (game3Manager.getWidth() - 1)) {
      setX(getX() + 1);
    }
  }

  /**
   * Return whether or not the player would fall down into the sewer.
   *
   * @return a boolean indicates whether or not the player would fall down into the sewer.
   */
  boolean wouldFall() {
    for (Sewer sewer : game3Manager.getSewers()) {
      if (getRectangle().intersect(sewer.getRectangle())) {
        return true;
      }
    }
    return false;
  }

  /** Update the rect that represents this item. */
  void updateRec() {
    int scale = Game3View.getScale();
    int left = getX();
    int top = getY();
    setRectangle(new Rect(left * scale, top * scale, (left + 1) * scale, (top + 1) * scale));
  }

  /**
   * Draw the Player in the map.*
   *
   * @param canvas the canvas on which to draw this player.
   */
  public abstract void draw(Canvas canvas);

  /** Player fall into the Sewer. */
  public abstract void fall();

  /** Causes this player update its status and location. */
  @Override
  public void update() {
    if (wouldFall()) {
      fall();
    }
    updateRec();
  }
}
