package com.example.triathlonova.game3.model.item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameItem;

/**
 * A hole that if pusher fall into it would get reset and sheep fall into it would give the pusher 1
 * score.
 */
public class Sewer extends GameItem {

  /** A bitmap for the sewer. */
  private Bitmap sewer;

  /**
   * Constructs a new sewer.
   *
   * @param rec the rect that represents this sewer.
   * @param context the environment.
   */
  public Sewer(Rect rec, Context context) {
    super(0, 0, rec);
    sewer = BitmapFactory.decodeResource(context.getResources(), R.drawable.lava);
  }

  /**
   * Draw the Sewer in the map.
   *
   * @param canvas the canvas on which to draw this sewer.
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(sewer, null, getRectangle(), null);
  }
}
