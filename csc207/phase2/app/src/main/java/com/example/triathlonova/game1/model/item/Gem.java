package com.example.triathlonova.game1.model.item;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.item.GameCollectibleItem;

/** An item in this game that the character must collect in order to win the game. */
public class Gem extends GameCollectibleItem {

  /**
   * Initialize this Gem object under this context by giving it a decoded bitmap image as appearance
   * and set its surrounding rectangle to the parameter rect.
   *
   * @param rect The rectangle shape that embodies this item.
   * @param context the environment.
   */
  public Gem(Rect rect, Context context) {
    super(rect, BitmapFactory.decodeResource(context.getResources(), R.drawable.gem));
  }
}
