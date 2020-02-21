package com.example.triathlonova.game.model.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/** A class of any object in this game. */
public abstract class GameItem {

  /** The x-coordinate of this item. */
  private int x;
  /** The y-coordinate of this item. */
  private int y;
  /** The bitmap image of this item. */
  private Bitmap itemPic;
  /** The width of this item. */
  private int width;
  /** The height of this item. */
  private int height;
  /** A rectangle that is around this item. */
  private Rect rectangle;

  public GameItem() {}

  /**
   * Initialize this item with the passed-in rectangle set around it and a bitmap image as its
   * appearance.
   *
   * @param rectangle a rectangle around this item.
   * @param bitmap the image of this item.
   */
  public GameItem(Rect rectangle, Bitmap bitmap) {
    this.rectangle = rectangle;
    this.itemPic = bitmap;
  }

  /**
   * Initialize this item with the passed-in rectangle set around it and located at (x, y) in the
   * coordinate grid.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   * @param rectangle a rectangle around this item.
   */
  public GameItem(int x, int y, Rect rectangle) {
    this.x = x;
    this.y = y;
    this.rectangle = rectangle;
  }

  /**
   * Initialize this item with the passed-in bitmap as its displayed look and located at (x, y) in
   * the coordinate grid, with dimension width and height.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   * @param width how wide this item is.
   * @param height how tall this item is.
   * @param bitmap the image of this item.
   */
  public GameItem(int x, int y, int width, int height, Bitmap bitmap) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.itemPic = bitmap;
    setRectangle(new Rect(x, y, x + width * bitmap.getWidth(), y + height * bitmap.getHeight()));
  }

  public Rect getRectangle() {
    return rectangle;
  }

  public void setRectangle(Rect rectangle) {
    this.rectangle = rectangle;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  protected Bitmap getItemPic() {
    return itemPic;
  }

  protected void setItemPic(Bitmap itemPic) {
    this.itemPic = itemPic;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public abstract void draw(Canvas canvas);
}
