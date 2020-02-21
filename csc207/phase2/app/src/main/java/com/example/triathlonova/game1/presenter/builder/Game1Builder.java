package com.example.triathlonova.game1.presenter.builder;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.triathlonova.game1.model.hero.Game1Hero;
import com.example.triathlonova.game1.model.item.Gem;
import com.example.triathlonova.game1.model.item.Hearts;
import com.example.triathlonova.game1.model.item.Stairs;

import java.util.List;

/** An interface of initialization of in-game objects. */
public interface Game1Builder {

  /**
   * Initialize and return a jumper constructed under this context.
   *
   * @param context the environment.
   * @return a constructed jumper.
   */
  Game1Hero buildGame1Hero(Context context);

  /**
   * Initialize some stairs (based on different version of the game map) under this context and
   * return a list containing all of the constructed stairs.
   *
   * @param context the environment.
   * @return a constructed list of initialized stairs.
   */
  List<Stairs> buildStairs(Context context);

  /**
   * Initialize some gems (based on different version of the game map) under this context and return
   * a list containing all of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  List<Gem> buildGem(Context context);

  /**
   * Initialize some hearts (based on different version of the game map) under this context and
   * return a list containing all of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  List<Hearts> buildHearts(Context context);

  /**
   * Initialize the background (based on different level of the game) under this context and return
   * it.
   *
   * @param context the environment.
   * @return an initialized and set game background.
   */
  Bitmap buildBackground(Context context);
}
