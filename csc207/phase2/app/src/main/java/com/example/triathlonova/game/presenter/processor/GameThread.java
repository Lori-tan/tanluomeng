package com.example.triathlonova.game.presenter.processor;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.triathlonova.game.view.GameView;

import static java.lang.Thread.sleep;

/** A thread of execution of this app. */
public abstract class GameThread implements Runnable {

  /** The view class that's responsible for displaying the items. */
  private final SurfaceHolder surfaceHolder;
  /** The host on which to let the view class to drawManager the items. */
  private Canvas canvas;
  /** A view class that starts this thread. */
  private GameView gameView;
  /** Indicating whether this view class is currently drawing. */
  private boolean isRunning;

  /**
   * Construct this thread.
   *
   * @param surfaceHolder the canvas container.
   * @param gameView where the items are drawn.
   */
  public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
    this.surfaceHolder = surfaceHolder;
    this.gameView = gameView;
  }

  /**
   * Return the drawing board.
   *
   * @return the canvas.
   */
  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Set the drawing board to this parameter.
   *
   * @param canvas the drawing board.
   */
  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  private SurfaceHolder getSurfaceHolder() {
    return surfaceHolder;
  }

  private boolean isRunning() {
    return isRunning;
  }

  /**
   * Set the value of isRunning variable to isRunning.
   *
   * @param isRunning a boolean value that is going to be set to as.
   */
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }

  /**
   * Continue to update the view in the game while the thread is running through contacting
   * towerGameView class to drawManager and update in-game objects.
   */
  @Override
  public void run() {
    while (isRunning()) {
      setCanvas(null);
      try {
        setCanvas(this.getSurfaceHolder().lockCanvas());
        synchronized (getSurfaceHolder()) {
          this.gameView.update();
          this.gameView.draw(getCanvas());
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (getCanvas() != null) {
          try {
            getSurfaceHolder().unlockCanvasAndPost(getCanvas());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      try {
        sleep(10);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
