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
 * Building obstacle: when a Balloon collides with a Building, the Balloon lose a life The Building
 * obstacles have dimension of 430*600 with 6 different image. They always appears at the bottom of
 * the screen
 */
public class Building extends GameCollideItem implements Updatable, Removable {
  /** The horizontal velocity of this object. */
  private int velocityX;

  /**
   * Initialize this building object under this context by giving it a corresponding type of decoded
   * bitmap image as appearance and set its surrounding rectangle to the parameter rect.
   *
   * @param type The type of the building.
   * @param x The x coordinate of the top left point of the building.
   * @param y The y coordinate of the top left point of the building.
   * @param rect The rectangle shape of the building.
   * @param context The environment.
   */
  public Building(int type, int x, int y, Rect rect, Context context) {
    super(x, y, rect);
    Bitmap buildingBitmap;
    if (type == 1)
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type1);
    else if (type == 2)
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type2);
    else if (type == 3)
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type3);
    else if (type == 4)
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type4);
    else if (type == 5)
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type5);
    else
      buildingBitmap =
          BitmapFactory.decodeResource(context.getResources(), R.drawable.building_type6);
    setItemPic(Bitmap.createScaledBitmap(buildingBitmap, 430, 600, false));
    velocityX = 5;
  }

  /** Buildings move to the left */
  public void update() {
    toLeft();
  }

  /** Draw the building's bitmap. */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** the algorithm to calculate and design how coordinates of Building change */
  private void toLeft() {
    int newX = getX() - velocityX;
    setX(newX);
    getRectangle().set(new Rect(newX, getY(), newX + 430, getY() + 600));
  }

  /**
   * whenever a building is collided by the balloon, remove this building by setting the coordinates
   * to negative number so it is outside the screen.
   */
  public void remove() {
    setX(-1000);
    setY(-1000);
    getRectangle().set(-1000, -1000, -1000, -1000);
  }
}
