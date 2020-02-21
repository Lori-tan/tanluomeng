package com.example.triathlonova.game2.model.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameCollideItem;
import com.example.triathlonova.helper.interfaces.Removable;
import com.example.triathlonova.helper.interfaces.Updatable;

/**
 * Lightning obstacle: when a Balloon collides with a lightning, the Balloon lose a life The
 * Building obstacles have dimension of 250*500 with 1 image. They always appears at the top of the
 * screen
 */
public class Lightning extends GameCollideItem implements Updatable, Removable {

  private int velocityX;

  /**
   * Initialize this cloud object under this context by giving its decoded bitmap image as
   * appearance and set its surrounding rectangle to the parameter rect.
   *
   * @param x The x coordinate of the top left point of the lightning.
   * @param y The y coordinate of the top left point of the lightning.
   * @param rect The rectangle shape of the lightning.
   * @param context The environment.
   */
  public Lightning(int x, int y, Rect rect, Context context) {
    super(x, y, rect);
    setItemPic(
        Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.lightning_frame1),
            250,
            500,
            false));
    velocityX = 5;
  }

  /** Lightning moves to the left */
  public void update() {
    toLeft();
  }

  /** Draw the lightning's bitmap */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** the algorithm to calculate and design how coordinates of Gem change */
  private void toLeft() {
    int newX = getX() - velocityX;
    setX(newX);
    getRectangle().set(new Rect(newX, getY(), newX + 250, getY() + 500));
  }

  /**
   * whenever a lightning is collided by the balloon, remove this lightning by setting the
   * coordinates to negative number so it is outside the screen.
   */
  public void remove() {
    setX(-1000);
    setY(-1000);
    getRectangle().set(-1000, -1000, -1000, -1000);
  }
}
