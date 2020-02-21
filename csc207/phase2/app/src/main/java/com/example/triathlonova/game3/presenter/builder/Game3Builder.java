package com.example.triathlonova.game3.presenter.builder;

import android.content.Context;

/** An interface of initialization of in-game objects. */
public interface Game3Builder {

  /**
   * Initialize and build a sheep pusher under this context.
   *
   * @param context the environment.
   */
  void buildPusher(Context context);

  /**
   * Build several sheep in different locations based on the level of the game under this context.
   *
   * @param context the environment.
   */
  void buildSheep(Context context);

  /**
   * Build several sewer in different locations based on the level of the game under this context.
   *
   * @param context the environment.
   */
  void buildSewers(Context context);
}
