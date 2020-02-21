package com.example.triathlonova.game2.model.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameCollectibleItem;

/**
 * Gem: when game2Hero collides with a Gem, it gains 1 score. Gems have dimension of 100*100 with 1
 * image, or a dimension of 252*2160 when it is the last gem, i.e. When game2Hero collides with it,
 * game 2 ends and entering game 3.
 */
public class Gem extends GameCollectibleItem {
  /** The horizontal velocity of this object. */
  private int velocityX;
  /** The boolean value to determine if this gem is the last gem */
  private boolean isLastGem;

  /**
   * Initialize this gem object under this context by giving it a corresponding type of decoded
   * bitmap image as appearance and set its surrounding rectangle to the parameter rect.
   *
   * @param x The x coordinate of the top left point of the gem.
   * @param y The y coordinate of the top left point of the gem.
   * @param lastGem True if the gem is the last gem, else false.
   * @param rect The rectangle shape of the gem.
   * @param context The environment.
   */
  public Gem(int x, int y, boolean lastGem, Rect rect, Context context) {
    super(x, y, rect);
    isLastGem = lastGem;
    if (!isLastGem) {
      setItemPic(
          Bitmap.createScaledBitmap(
              BitmapFactory.decodeResource(context.getResources(), R.drawable.gem),
              100,
              100,
              false));
    } else {
      setItemPic(
          Bitmap.createScaledBitmap(
              BitmapFactory.decodeResource(context.getResources(), R.drawable.hot_air_balloon_gems),
              252,
              2160,
              false));
    }
    velocityX = 5;
  }

  /** Gem moves to the left */
  public void update() {
    toLeft();
  }

  /** Draw the gem's bitmap */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** the algorithm to calculate and design how coordinates of Gem change */
  private void toLeft() {
    int newX = getX() - velocityX;
    setX(newX);
    if (!isLastGem) getRectangle().set(new Rect(newX, getY(), newX + 100, getY() + 100));
    else getRectangle().set(new Rect(newX, getY(), newX + 252, getY() + 2160));
  }

  /**
   * whenever a gem is collided by the balloon, remove this gem by setting the coordinates to
   * negative number so it is outside the screen.
   */
  public void remove() {
    setX(-1000);
    setY(-1000);
    getRectangle().set(-1000, -1000, -1000, -1000);
  }
}
