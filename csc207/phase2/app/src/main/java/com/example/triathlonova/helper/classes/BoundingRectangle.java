package com.example.triathlonova.helper.classes;

import android.graphics.Rect;

import com.example.triathlonova.helper.interfaces.SpecificCollide;

import java.util.ArrayList;
import java.util.List;

/** A helper class of rectangle that detects collision in multiple directions. */
public class BoundingRectangle implements SpecificCollide {
  private List<Rect> boundList;
  private Rect leftBound;
  private Rect rightBound;
  private Rect topBound;
  private Rect bottomBound;

  /**
   * Create the bounding rectangle by inputting the required rectangle.
   *
   * @param rect the required rectangle.
   */
  public BoundingRectangle(Rect rect) {
    int width = 4;
    leftBound = new Rect(rect.left, rect.top, rect.left + width, rect.bottom);
    rightBound = new Rect(rect.right - width, rect.top, rect.right, rect.bottom);
    topBound = new Rect(rect.left + width, rect.top, rect.right - width, rect.top + width);
    bottomBound = new Rect(rect.left + width, rect.bottom - width, rect.right - width, rect.bottom);
    boundList = new ArrayList<>();
    boundList.add(leftBound);
    boundList.add(rightBound);
    boundList.add(topBound);
    boundList.add(bottomBound);
  }

  /**
   * Test if the rectangle was collided from the top.
   *
   * @param rect the required rectangle.
   * @return true if the rectangle has collided from the top.
   */
  public boolean topCollide(Rect rect) {
    return Rect.intersects(rect, topBound);
  }

  /**
   * Test if the rectangle was collided from the bottom.
   *
   * @param rect the required rectangle.
   * @return true if the rectangle has collided from the bottom.
   */
  public boolean bottomCollide(Rect rect) {
    return Rect.intersects(rect, bottomBound);
  }

  /**
   * Test if the rectangle was collided from the left.
   *
   * @param rect the required rectangle.
   * @return true if the rectangle has collided from the left.
   */
  public boolean leftCollide(Rect rect) {
    return Rect.intersects(rect, leftBound);
  }

  /**
   * Test if the rectangle was collided from the right.
   *
   * @param rect the required rectangle.
   * @return true if the rectangle has been collided from the right.
   */
  public boolean rightCollide(Rect rect) {
    return Rect.intersects(rect, rightBound);
  }

}
