package com.example.triathlonova.game2.presenter.builder;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.triathlonova.game2.model.hero.Game2Hero;
import com.example.triathlonova.game2.model.item.Building;
import com.example.triathlonova.game2.model.item.Cloud;
import com.example.triathlonova.game2.model.item.Gem;
import com.example.triathlonova.game2.model.item.Lightning;

import java.util.List;

/** An interface of initialization of in-game objects. */
public interface Game2Builder {

  /**
   * Initialize and return a game2Hero constructed under this context.
   *
   * @param context the environment.
   * @return a constructed jumper.
   */
  Game2Hero buildGame2Hero(Context context);

  /**
   * Initialize some gems (based on different version of the game map) under this context and return
   * a list containing all of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  List<Gem> buildGems(Context context);

  /**
   * Initialize some lightnings (based on different version of the game map) under this context and
   * return a list containing all of the constructed lightnings.
   *
   * @param context the environment.
   * @return a constructed list of initialized lightnings.
   */
  List<Lightning> buildLightning(Context context);

  /**
   * Initialize some buildings (based on different version of the game map) under this context and
   * return a list containing all of the constructed buildings.
   *
   * @param context the environment.
   * @return a constructed list of initialized buildings.
   */
  List<Building> buildBuilding(Context context);

  /**
   * Initialize some clouds (based on different version of the game map) under this context and
   * return a list containing all of the constructed clouds.
   *
   * @param context the environment.
   * @return a constructed list of initialized clouds.
   */
  List<Cloud> buildCloud(Context context);

  /**
   * Initialize the background (based on different level of the game) under this context and return
   * it.
   *
   * @param context the environment.
   * @return an initialized and set game background.
   */
  Bitmap buildBackground(Context context);
}
