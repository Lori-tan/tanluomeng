package com.example.triathlonova.game2.presenter.builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.presenter.builder.GameBuilder;
import com.example.triathlonova.game.view.GameView;
import com.example.triathlonova.game2.model.hero.Game2Hero;
import com.example.triathlonova.game2.model.item.Building;
import com.example.triathlonova.game2.model.item.Cloud;
import com.example.triathlonova.game2.model.item.Gem;
import com.example.triathlonova.game2.model.item.Lightning;

import java.util.ArrayList;
import java.util.List;

/**
 * Level1Builder for game2: this level initiates a game with length of approximately 2000 pixels
 * units. It has 3 gems with gaps of 600 units, 3 lightnings with gaps of 400 units, 2 buildings
 * with gaps of 600 units, and 2 clouds with gaps of 700 units.
 */
public class Level1Builder extends GameBuilder implements Game2Builder {

  /**
   * Initialize and return a balloon constructed under this context.
   *
   * @param context the environment.
   * @return a constructed balloon.
   */
  @Override
  public Game2Hero buildGame2Hero(Context context) {
    return new Game2Hero(
        new Rect(
            GameView.getScreenWidth() / 3,
            GameView.getScreenHeight() / 2,
            GameView.getScreenWidth() / 3 + 200,
            GameView.getScreenHeight() / 2 + 300),
        context);
  }

  /**
   * Initialize some gems (based on level1 map) under this context and return a list containing all
   * of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  @Override
  public List<Gem> buildGems(Context context) {
    List<Gem> gemList = new ArrayList<>();
    gemList.add(new Gem(1200, 720, false, new Rect(1200, 720, 1200 + 100, 720 + 100), context));

    gemList.add(
        new Gem(
            1200 + 600,
            850,
            false,
            new Rect(1200 + 600, 850, 1200 + 600 + 100, 850 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 600 * 2,
            0,
            true,
            new Rect(1200 + 600 * 2, 0, 1200 + 600 * 2 + 252, 2160),
            context));
    return gemList;
  }

  /**
   * Initialize some lightnings (based on level1 map) under this context and return a list
   * containing all of the constructed lightnings.
   *
   * @param context the environment.
   * @return a constructed list of initialized lightning.
   */
  @Override
  public List<Lightning> buildLightning(Context context) {
    List<Lightning> lightningList = new ArrayList<>();
    lightningList.add(new Lightning(1000, 20, new Rect(1000, 20, 1000 + 250, 20 + 500), context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 2,
            110,
            new Rect(1000 + 400 * 2, 110, 1000 + 400 * 2 + 250, 110 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 3,
            30,
            new Rect(1000 + 400 * 3, 30, 1000 + 400 * 3 + 250, 30 + 500),
            context));

    return lightningList;
  }

  /**
   * Initialize some buildings (based on level1 map) under this context and return a list containing
   * all of the constructed buildings.
   *
   * @param context the environment.
   * @return a constructed list of initialized buildings.
   */
  @Override
  public List<Building> buildBuilding(Context context) {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(
        new Building(1, 1100, 2100 - 600, new Rect(1100, 2100 - 600, 1100 + 430, 2100), context));
    buildingList.add(
        new Building(
            2,
            1100 + 900,
            2100 - 600,
            new Rect(1100 + 900, 2100 - 600, 1100 + 900 + 430, 2100),
            context));

    return buildingList;
  }

  /**
   * Initialize some clouds (based on level1 map) under this context and return a list containing
   * all of the constructed clouds.
   *
   * @param context the environment.
   * @return a constructed list of initialized clouds.
   */
  @Override
  public List<Cloud> buildCloud(Context context) {
    List<Cloud> cloudList = new ArrayList<>();
    cloudList.add(new Cloud(1, 1300, 1200, new Rect(1300, 1200, 1300 + 200, 1200 + 150), context));

    cloudList.add(
        new Cloud(
            2, 1300 + 700, 700, new Rect(1300 + 700, 700, 1300 + 700 + 200, 700 + 150), context));

    return cloudList;
  }

  /**
   * Initialize the background of level1 of the game under this context and return it.
   *
   * @param context the environment.
   * @return an initialized and set game background.
   */
  @Override
  public Bitmap buildBackground(Context context) {
    return BitmapFactory.decodeResource(
        context.getResources(), R.drawable.hot_air_balloon_background);
  }
}
