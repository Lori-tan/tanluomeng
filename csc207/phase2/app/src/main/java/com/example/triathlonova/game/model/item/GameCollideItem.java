package com.example.triathlonova.game.model.item;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.triathlonova.helper.classes.BoundingRectangle;

/** An item in the game that can collide with another and generate a reaction. */
public abstract class GameCollideItem extends GameItem {

  /** A helper rectangle that detects collision. */
  private BoundingRectangle boundingRectangle;

  /**
   * Initialize this special type of item.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   * @param width how wide this item is.
   * @param height how tall this item is.
   * @param bitmap the image of this item.
   */
  public GameCollideItem(int x, int y, int width, int height, Bitmap bitmap) {
    super(x, y, width, height, bitmap);
    setRectangle(new Rect(x, y, x + width * bitmap.getWidth(), y + height * bitmap.getHeight()));
    boundingRectangle = new BoundingRectangle(getRectangle());
  }

  /**
   * Initialize this special type of item.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   * @param rect a rectangle around this item.
   */
  public GameCollideItem(int x, int y, Rect rect) {
    super(x, y, rect);
    boundingRectangle = new BoundingRectangle(getRectangle());
  }

  /**
   * Return a boolean indicating whether this item intersects another game item at its top.
   *
   * @param gameItem an item in the game.
   * @return true iff the rectangle around this item intersects with that around another item.
   */
  public boolean topCollided(GameItem gameItem) {
    return boundingRectangle.topCollide(gameItem.getRectangle());
  }

  /**
   * Return a boolean indicating whether this item intersects another game item at its bottom.
   *
   * @param gameItem an item in the game.
   * @return true iff the rectangle around this item intersects with that around another item.
   */
  public boolean bottomCollided(GameItem gameItem) {
    return boundingRectangle.bottomCollide(gameItem.getRectangle());
  }

  /**
   * Return a boolean indicating whether this item intersects another game item at its sides.
   *
   * @param gameItem an item in the game.
   * @return true iff the rectangle around this item intersects with that around another item.
   */
  public boolean horizontallyCollided(GameItem gameItem) {
    return boundingRectangle.leftCollide(gameItem.getRectangle())
        || boundingRectangle.rightCollide(gameItem.getRectangle());
  }
}
