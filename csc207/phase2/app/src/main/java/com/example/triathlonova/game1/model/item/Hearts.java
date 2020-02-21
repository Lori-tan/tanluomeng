package com.example.triathlonova.game1.model.item;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameCollectibleItem;

/** An item in this game that the character may collect to earn one more life. */
public class Hearts extends GameCollectibleItem {

  /**
   * Initialize this Hearts object under this context by giving it a decoded bitmap image as
   * appearance and set its surrounding rectangle to the parameter rect.
   *
   * @param rect The rectangle shape that embodies this item.
   * @param context the environment.
   */
  public Hearts(Rect rect, Context context) {
    super(rect, BitmapFactory.decodeResource(context.getResources(), R.drawable.heart));
  }
}
