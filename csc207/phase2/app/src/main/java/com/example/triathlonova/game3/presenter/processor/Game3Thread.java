package com.example.triathlonova.game3.presenter.processor;

import android.view.SurfaceHolder;

import com.example.triathlonova.game.presenter.processor.GameThread;
import com.example.triathlonova.game3.view.Game3View;

/** A thread of execution of Game3. */
public class Game3Thread extends GameThread {

  /**
   * Construct the thread.
   *
   * @param surfaceHolder the canvas container.
   * @param game3View where the game items are drawn.
   */
  public Game3Thread(SurfaceHolder surfaceHolder, Game3View game3View) {
    super(surfaceHolder, game3View);
  }
}
