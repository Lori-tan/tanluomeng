package com.example.triathlonova.game2.presenter.processor;

import android.view.SurfaceHolder;

import com.example.triathlonova.game.presenter.processor.GameThread;
import com.example.triathlonova.game.view.GameView;

/** A thread of execution of Game2. */
public class Game2Thread extends GameThread {
  public Game2Thread(SurfaceHolder surfaceHolder, GameView gameView) {
    super(surfaceHolder, gameView);
  }
}
