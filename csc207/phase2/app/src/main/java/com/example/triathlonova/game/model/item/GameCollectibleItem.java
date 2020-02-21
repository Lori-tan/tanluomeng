package com.example.triathlonova.game.model.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/** An item that can be collected by the hero during game. */
public abstract class GameCollectibleItem extends GameItem {

  /**
   * Initialize this special type of item.
   *
   * @param rect a rectangle around this item.
   * @param bitmap the image of this item.
   */
  public GameCollectibleItem(Rect rect, Bitmap bitmap) {
    super(rect, bitmap);
  }

  /**
   * Initialize this special type of item.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   * @param rect a rectangle around this item.
   */
  public GameCollectibleItem(int x, int y, Rect rect) {
    super(x, y, rect);
  }

  /**
   * Return whether the rectangle embodies this gem item intersects with the parameter rect
   * rectangle.
   *
   * @param rect another rectangle to compare with
   * @return true iff the two rectangles intersect.
   */
  public boolean collidedWith(Rect rect) {
    return Rect.intersects(rect, getRectangle());
  }

  /**
   * Draw a gem onto this canvas with its initialized bitmap image.
   *
   * @param canvas the host to this drawManager call.
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), null, getRectangle(), null);
  }
}
