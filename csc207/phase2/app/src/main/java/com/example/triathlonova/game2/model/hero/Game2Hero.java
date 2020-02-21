package com.example.triathlonova.game2.model.hero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.hero.GameHero;
import com.example.triathlonova.game.view.GameView;

/**
 * Game2Hero is a Balloon. If it collide with Building, Lightning, or Cloud, it will lose a life If
 * it collide with Gems, it gains 1 score.
 */
public class Game2Hero extends GameHero {
  /** The amount the character moves in the vertical direction. */
  private int velocityY;
  /** The natural floating acceleration. */
  private int buoyancy;
  /** The width of the bitmap for game2Hero. */
  private int bitmapWidth;
  /** The height of the bitmap for game2Hero. */
  private int bitmapHeight;

  /**
   * Initialize this Game2Hero object with the given rectangle under this context.
   *
   * @param rect the rectangle around this hero.
   * @param context the environment.
   */
  public Game2Hero(Rect rect, Context context) {
    super(GameView.getScreenWidth() / 3, GameView.getScreenHeight() / 2, rect);
    bitmapWidth = 200;
    bitmapHeight = 300;
    setItemPic(
        Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.hot_air_balloon_frame1),
            bitmapWidth,
            bitmapHeight,
            false));
    setRectangle(new Rect(getX(), getY(), getX() + bitmapWidth, getY() + bitmapHeight));
    velocityY = 0;
    buoyancy = 1;
  }

  /** when the balloon dies, it goes back to the initial position */
  @Override
  protected void newLife() {
    getRectangle().set(getX(), getY(), getX() + bitmapWidth, getY() + bitmapHeight);
  }

  /**
   * draw the balloon
   *
   * @param canvas the host on which to this draw call.
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** update method for the balloon, if button is not touched, the balloon will keep floating */
  @Override
  public void update() {
    floating();
  }

  /** the algorithm to calculate and design how coordinates of balloon change */
  private void floating() {
    if (getY() > 0) {
      velocityY += buoyancy;
    } else {
      velocityY = 0;
    }
    setY(getY() - velocityY);
    getRectangle().set(getX(), getY(), getX() + bitmapWidth, getY() + bitmapHeight);
  }

  /** whenever the flyDownButton is touched, the balloon will sink instead of float */
  public void sinking() {
    if (getY() + getItemPic().getHeight() < GameView.getScreenHeight()) {
      velocityY = -15;
      setY(getY() - velocityY);
      getRectangle().set(getX(), getY(), getX() + bitmapWidth, getY() + bitmapHeight);
    }
  }

  /**
   * Return true if game2Hero collided with another object, otherwise false.
   *
   * @param rect The rectangle of another object.
   * @return a boolean telling whether the balloon collides with obstacles or gem
   */
  public boolean collidedWith(Rect rect) {
    return Rect.intersects(rect, getRectangle());
  }
}
