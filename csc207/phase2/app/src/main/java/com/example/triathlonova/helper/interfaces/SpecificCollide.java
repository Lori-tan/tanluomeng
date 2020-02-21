package com.example.triathlonova.helper.interfaces;

import android.graphics.Rect;

/**
 * An interface that allow checking specifically if the rectangles has been collided either from the
 * top, bottom, left or right.
 */
public interface SpecificCollide {
  boolean topCollide(Rect rect);

  boolean bottomCollide(Rect rect);

  boolean leftCollide(Rect rect);

  boolean rightCollide(Rect rect);
}
