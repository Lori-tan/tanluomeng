package com.example.triathlonova.game.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.triathlonova.game.presenter.processor.GameThread;

/** A class that makes the GameManager class to draw and update items. */
public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

  /** Screen width. */
  private static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  /** The scaled size of GameManager gird. */
  private static int scale = Math.round(screenWidth / 15);
  /** The time-tracking part of the program. */
  private GameThread gameThread;
  /** The bitmap image of the background of the game. */
  private Bitmap background;
  /** The rectangle of the whole screen display. */
  private Rect rect;

  public GameView(Context context) {
    super(context);
  }

  public GameView(Context context, GameThread gameThread) {
    super(context);
    this.gameThread = gameThread;
  }

  /**
   * Return the value of screenWidth.
   *
   * @return the screenWidth variable.
   */
  public static int getScreenWidth() {
    return screenWidth;
  }

  /**
   * Return the value of screenHeight.
   *
   * @return the screenHeight variable
   */
  public static int getScreenHeight() {
    return screenHeight;
  }

  /**
   * Return the value of scale.
   *
   * @return the value of scale variable.
   */
  public static int getScale() {
    return scale;
  }

  public GameThread getGameThread() {
    return gameThread;
  }

  public void setGameThread(GameThread gameThread) {
    this.gameThread = gameThread;
  }

  public Bitmap getThisBackground() {
    return background;
  }

  public Rect getRect() {
    return rect;
  }

  public void setRect(Rect rect) {
    this.rect = rect;
  }

  public void setBackground(Bitmap background) {
    this.background = background;
  }

  /** Make the GameManager class to update the items in the game. */
  public abstract void update();

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  /**
   * Start running the thread of this game.
   *
   * @param holder an interface to hold the display surface.
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    gameThread.setRunning(true);
    new Thread(gameThread).start();
  }

  /**
   * Based on certain situation to stop the thread from running.
   *
   * @param holder an interface to hold the display surface.
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        gameThread.setRunning(false);
      } catch (Exception e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }
}
