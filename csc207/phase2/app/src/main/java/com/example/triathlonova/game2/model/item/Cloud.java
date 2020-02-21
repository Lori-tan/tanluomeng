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
 * Cloud obstacle: when a Balloon collides with a Cloud, the Balloon lose a life The Building
 * obstacles have dimension of 200*150 with 6 different image. They always appears in the middle of
 * the screen, making it hard for the balloon to go through
 */
public class Cloud extends GameCollideItem implements Updatable, Removable {
  /** The horizontal velocity of this object. */
  private int velocityX;

  /**
   * Initialize this cloud object under this context by giving it a corresponding type of decoded
   * bitmap image as appearance and set its surrounding rectangle to the parameter rect.
   *
   * @param type The type of the cloud.
   * @param x The x coordinate of the top left point of the cloud.
   * @param y The y coordinate of the top left point of the cloud.
   * @param rect The rectangle shape of the cloud.
   * @param context The environment.
   */
  public Cloud(int type, int x, int y, Rect rect, Context context) {
    super(x, y, rect);
    Bitmap buildingBitmap;
    if (type == 1)
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type1);
    else if (type == 2)
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type2);
    else if (type == 3)
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type3);
    else if (type == 4)
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type4);
    else if (type == 5)
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type5);
    else
      buildingBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_type6);
    setItemPic(Bitmap.createScaledBitmap(buildingBitmap, 200, 150, false));
    velocityX = 5;
  }

  /** Cloud moves to the left */
  public void update() {
    toLeft();
  }

  /** Draw the cloud's bitmap */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** the algorithm to calculate and design how coordinates of Cloud change */
  private void toLeft() {
    int newX = getX() - velocityX;
    setX(newX);
    getRectangle().set(new Rect(newX, getY(), newX + 200, getY() + 150));
  }

  public void remove() {
    setX(-1000);
    setY(-1000);
    getRectangle().set(-1000, -1000, -1000, -1000);
  }
}
