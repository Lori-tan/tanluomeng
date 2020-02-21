package com.example.triathlonova.game1.presenter.processor;

import android.view.SurfaceHolder;

import com.example.triathlonova.game.presenter.processor.GameThread;
import com.example.triathlonova.game1.view.Game1View;

/** A thread of execution of Game1. */
public class Game1Thread extends GameThread {

  /**
   * Construct this thread.
   *
   * @param surfaceHolder the canvas container.
   * @param view where the game items are drawn.
   */
  public Game1Thread(SurfaceHolder surfaceHolder, Game1View view) {
    super(surfaceHolder, view);
  }
}
