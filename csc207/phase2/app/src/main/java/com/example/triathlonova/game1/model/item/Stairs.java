package com.example.triathlonova.game1.model.item;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameCollideItem;

/** An item in this game that the character can step on as platforms. */
public class Stairs extends GameCollideItem {

  /**
   * Initialize this Stairs object under this context by giving it a decoded bitmap image as
   * appearance and set its location to (x, y) with width and height as its dimension.
   *
   * @param x the first coordinate.
   * @param y the second coordinate.
   * @param width the width.
   * @param height the height.
   * @param context the environment
   */
  public Stairs(int x, int y, int width, int height, Context context) {
    super(
        x,
        y,
        width,
        height,
        BitmapFactory.decodeResource(context.getResources(), R.drawable.stair));
  }

  /**
   * Draw this stairs onto this canvas with its initialized bitmap image.
   *
   * @param canvas the host to this draw call.
   */
  @Override
  public void draw(Canvas canvas) {
    for (int i = 0; i < getWidth(); ++i) {
      for (int j = 0; j < getHeight(); ++j) {
        canvas.drawBitmap(getItemPic(), null, getRectangle(), null);
      }
    }
  }
}
