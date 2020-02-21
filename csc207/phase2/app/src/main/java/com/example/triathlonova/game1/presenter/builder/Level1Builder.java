package com.example.triathlonova.game1.presenter.builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.presenter.builder.GameBuilder;
import com.example.triathlonova.game1.model.hero.Game1Hero;
import com.example.triathlonova.game1.model.item.Gem;
import com.example.triathlonova.game1.model.item.Hearts;
import com.example.triathlonova.game1.model.item.Stairs;
import com.example.triathlonova.game1.view.Game1View;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder class of Game1 which has the responsibility of initializing all in-game objects, the
 * jumper, stairs, and gem. The builder design pattern is applied here to facilitate the process of
 * further constructing maps in the next phase.
 */
class Level1Builder extends GameBuilder implements Game1Builder {

  /**
   * Initialize this Level1Builder by setting the scaled size of the character to a value calculated
   * and store in TowerGameView class. This enables easy adjustments of character size whenever
   * needed in the next phase.
   */
  Level1Builder() {
    setCharacterScale(Game1View.getScale());
  }

  /**
   * Initialize and return a jumper constructed under this context.
   *
   * @param context the environment.
   * @return a constructed jumper.
   */
  @Override
  public Game1Hero buildGame1Hero(Context context) {
    return new Game1Hero(
        new Rect(
            2 * getCharacterScale(),
            2 * getCharacterScale(),
            3 * getCharacterScale(),
            3 * getCharacterScale()),
        context);
  }

  /**
   * Initialize some stairs (based on level1 map) under this context and return a list containing
   * all of the constructed stairs.
   *
   * @param context the environment.
   * @return a constructed list of initialized stairs.
   */
  @Override
  public List<Stairs> buildStairs(Context context) {
    List<Stairs> stairsList = new ArrayList<>();
    Stairs stairs1 = new Stairs(100, 500, 3, 1, context);
    stairsList.add(stairs1);
    Stairs stairs2 = new Stairs(700, 1000, 1, 1, context);
    stairsList.add(stairs2);
    Stairs stairs3 = new Stairs(0, 1375, 5, 1, context);
    stairsList.add(stairs3);
    return stairsList;
  }

  /**
   * Initialize some gems (based on level1 map) under this context and return a list containing all
   * of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  @Override
  public List<Gem> buildGem(Context context) {
    List<Gem> gemList = new ArrayList<>();
    Gem gem1 = new Gem(new Rect(150, 400, 200, 450), context);
    gemList.add(gem1);
    return gemList;
  }

  /**
   * Initialize some hearts (based on level1 map) under this context and return a list containing
   * all of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  @Override
  public List<Hearts> buildHearts(Context context) {
    List<Hearts> heartsList = new ArrayList<>();
    Hearts hearts1 = new Hearts(new Rect(250, 400, 300, 450), context);
    heartsList.add(hearts1);
    return heartsList;
  }

  /**
   * Initialize the background of level1 of the game under this context and return it.
   *
   * @param context the environment.
   * @return an initialized and set game background.
   */
  @Override
  public Bitmap buildBackground(Context context) {
    return BitmapFactory.decodeResource(context.getResources(), R.drawable.game1_dungeon);
  }
}
