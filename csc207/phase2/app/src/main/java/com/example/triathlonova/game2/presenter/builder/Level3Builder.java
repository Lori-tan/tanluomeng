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
 * Level3Builder for game2: this level initiates a game with length of approximately 10000 pixels
 * units. It has 12 gems with gaps of 600 units, 23 lightnings with gaps of 400 units, 10 buildings
 * with gaps of 600 units, and 13 clouds with gaps of 700 units.
 */
public class Level3Builder extends GameBuilder implements Game2Builder {

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
   * Initialize some gems (based on level3 map) under this context and return a list containing all
   * of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  @Override
  public List<Gem> buildGems(Context context) {
    List<Gem> gemList = new ArrayList<>();
    gemList.add(new Gem(1200, 1200, false, new Rect(1200, 1000, 1200 + 100, 1000 + 100), context));
    gemList.add(
        new Gem(
            1200 + 800,
            660,
            false,
            new Rect(1200 + 800, 660, 1200 + 800 + 100, 660 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 2,
            1000,
            false,
            new Rect(1200 + 800 * 2, 900, 1200 + 800 * 2 + 100, 900 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 3,
            700,
            false,
            new Rect(1200 + 800 * 3, 700, 1200 + 800 * 3 + 100, 700 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 4,
            1330,
            false,
            new Rect(1200 + 800 * 4, 830, 1200 + 800 * 4 + 100, 830 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 5,
            970,
            false,
            new Rect(1200 + 800 * 5, 970, 1200 + 800 * 5 + 100, 970 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 6,
            760,
            false,
            new Rect(1200 + 800 * 6, 760, 1200 + 800 * 6 + 100, 760 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 7,
            1200,
            false,
            new Rect(1200 + 800 * 7, 900, 1200 + 800 * 7 + 100, 900 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 8,
            970,
            false,
            new Rect(1200 + 800 * 8, 670, 1200 + 800 * 8 + 100, 670 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 9,
            620,
            false,
            new Rect(1200 + 800 * 9, 820, 1200 + 800 * 9 + 100, 820 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 10,
            1340,
            false,
            new Rect(1200 + 800 * 10, 940, 1200 + 800 * 10 + 100, 940 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 11,
            0,
            true,
            new Rect(1200 + 800 * 11, 0, 1200 + 800 * 11 + 252, 2160),
            context));
    return gemList;
  }

  /**
   * Initialize some lightnings (based on level3 map) under this context and return a list
   * containing all of the constructed lightnings.
   *
   * @param context the environment.
   * @return a constructed list of initialized lightning.
   */
  @Override
  public List<Lightning> buildLightning(Context context) {
    List<Lightning> lightningList = new ArrayList<>();
    lightningList.add(
        new Lightning(1000, 160, new Rect(1000, 160, 1000 + 250, 160 + 500), context));
    lightningList.add(
        new Lightning(
            1000 + 400, 100, new Rect(1000 + 400, 100, 1000 + 400 + 250, 100 + 500), context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 2,
            130,
            new Rect(1000 + 400 * 2, 130, 1000 + 400 * 2 + 250, 130 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 3,
            90,
            new Rect(1000 + 400 * 3, 90, 1000 + 400 * 3 + 250, 90 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 4,
            150,
            new Rect(1000 + 400 * 4, 150, 1000 + 400 * 4 + 250, 150 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 5,
            180,
            new Rect(1000 + 400 * 5, 180, 1000 + 400 * 5 + 250, 180 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 6,
            160,
            new Rect(1000 + 400 * 6, 160, 1000 + 400 * 6 + 250, 160 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 7,
            110,
            new Rect(1000 + 400 * 7, 110, 1000 + 400 * 7 + 250, 110 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 8,
            140,
            new Rect(1000 + 400 * 8, 140, 1000 + 400 * 8 + 250, 140 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 9,
            100,
            new Rect(1000 + 400 * 9, 100, 1000 + 400 * 9 + 250, 100 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 10,
            160,
            new Rect(1000 + 400 * 10, 160, 1000 + 400 * 10 + 250, 160 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 11,
            120,
            new Rect(1000 + 400 * 11, 120, 1000 + 400 * 11 + 250, 120 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 12,
            170,
            new Rect(1000 + 400 * 12, 170, 1000 + 400 * 12 + 250, 170 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 13,
            100,
            new Rect(1000 + 400 * 13, 100, 1000 + 400 * 13 + 250, 100 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 14,
            130,
            new Rect(1000 + 400 * 14, 130, 1000 + 400 * 14 + 250, 130 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 15,
            150,
            new Rect(1000 + 400 * 15, 150, 1000 + 400 * 15 + 250, 150 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 16,
            160,
            new Rect(1000 + 400 * 16, 160, 1000 + 400 * 16 + 250, 160 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 17,
            120,
            new Rect(1000 + 400 * 17, 120, 1000 + 400 * 17 + 250, 120 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 18,
            150,
            new Rect(1000 + 400 * 18, 150, 1000 + 400 * 18 + 250, 150 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 19,
            170,
            new Rect(1000 + 400 * 19, 170, 1000 + 400 * 19 + 250, 170 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 20,
            130,
            new Rect(1000 + 400 * 20, 130, 1000 + 400 * 20 + 250, 130 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 21,
            160,
            new Rect(1000 + 400 * 21, 160, 1000 + 400 * 21 + 250, 160 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 22,
            110,
            new Rect(1000 + 400 * 22, 110, 1000 + 400 * 22 + 250, 110 + 500),
            context));
    return lightningList;
  }

  /**
   * Initialize some buildings (based on level3 map) under this context and return a list containing
   * all of the constructed buildings.
   *
   * @param context the environment.
   * @return a constructed list of initialized buildings.
   */
  @Override
  public List<Building> buildBuilding(Context context) {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(
        new Building(6, 1100, 2100 - 600, new Rect(1100, 2100 - 600, 1100 + 430, 2100), context));
    buildingList.add(
        new Building(
            2,
            1100 + 900,
            2100 - 600,
            new Rect(1100 + 900, 2100 - 600, 1100 + 900 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            3,
            1100 + 900 * 2,
            2100 - 600,
            new Rect(1100 + 900 * 2, 2100 - 600, 1100 + 900 * 2 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            5,
            1100 + 900 * 3,
            2100 - 600,
            new Rect(1100 + 900 * 3, 2100 - 600, 1100 + 900 * 3 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            1,
            1100 + 900 * 4,
            2100 - 600,
            new Rect(1100 + 900 * 4, 2100 - 600, 1100 + 900 * 4 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            4,
            1100 + 900 * 5,
            2100 - 600,
            new Rect(1100 + 900 * 5, 2100 - 600, 1100 + 900 * 5 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            5,
            1100 + 900 * 6,
            2100 - 600,
            new Rect(1100 + 900 * 6, 2100 - 600, 1100 + 900 * 6 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            1,
            1100 + 900 * 7,
            2100 - 600,
            new Rect(1100 + 900 * 7, 2100 - 600, 1100 + 900 * 7 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            6,
            1100 + 900 * 8,
            2100 - 600,
            new Rect(1100 + 900 * 8, 2100 - 600, 1100 + 900 * 8 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            3,
            1100 + 900 * 9,
            2100 - 600,
            new Rect(1100 + 900 * 9, 2100 - 600, 1100 + 900 * 9 + 430, 2100),
            context));
    return buildingList;
  }

  /**
   * Initialize some clouds (based on level3 map) under this context and return a list containing
   * all of the constructed clouds.
   *
   * @param context the environment.
   * @return a constructed list of initialized clouds.
   */
  @Override
  public List<Cloud> buildCloud(Context context) {
    List<Cloud> cloudList = new ArrayList<>();
    cloudList.add(new Cloud(5, 1300, 1200, new Rect(1300, 1200, 1300 + 200, 1200 + 150), context));
    cloudList.add(
        new Cloud(
            4, 1300 + 700, 920, new Rect(1300 + 700, 920, 1300 + 700 + 200, 920 + 150), context));
    cloudList.add(
        new Cloud(
            2,
            1300 + 700 * 2,
            1030,
            new Rect(1300 + 700 * 2, 1030, 1300 + 700 * 2 + 200, 1030 + 150),
            context));
    cloudList.add(
        new Cloud(
            3,
            1300 + 700 * 3,
            1160,
            new Rect(1300 + 700 * 3, 1160, 1300 + 700 * 3 + 200, 1160 + 150),
            context));
    cloudList.add(
        new Cloud(
            1,
            1300 + 700 * 4,
            880,
            new Rect(1300 + 700 * 4, 880, 1300 + 700 * 4 + 200, 880 + 150),
            context));
    cloudList.add(
        new Cloud(
            6,
            1300 + 700 * 5,
            1010,
            new Rect(1300 + 700 * 5, 1010, 1300 + 700 * 5 + 200, 1010 + 150),
            context));
    cloudList.add(
        new Cloud(
            2,
            1300 + 700 * 6,
            930,
            new Rect(1300 + 700 * 6, 930, 1300 + 700 * 6 + 200, 930 + 150),
            context));
    cloudList.add(
        new Cloud(
            4,
            1300 + 700 * 7,
            1060,
            new Rect(1300 + 700 * 7, 1060, 1300 + 700 * 7 + 200, 1060 + 150),
            context));
    cloudList.add(
        new Cloud(
            5,
            1300 + 700 * 8,
            950,
            new Rect(1300 + 700 * 8, 950, 1300 + 700 * 8 + 200, 950 + 150),
            context));
    cloudList.add(
        new Cloud(
            3,
            1300 + 700 * 9,
            1070,
            new Rect(1300 + 700 * 9, 1070, 1300 + 700 * 9 + 200, 1070 + 150),
            context));
    cloudList.add(
        new Cloud(
            1,
            1300 + 700 * 10,
            910,
            new Rect(1300 + 700 * 10, 910, 1300 + 700 * 10 + 200, 910 + 150),
            context));
    cloudList.add(
        new Cloud(
            2,
            1300 + 700 * 11,
            1140,
            new Rect(1300 + 700 * 11, 1140, 1300 + 700 * 11 + 200, 1140 + 150),
            context));
    cloudList.add(
        new Cloud(
            5,
            1300 + 700 * 12,
            970,
            new Rect(1300 + 700 * 12, 970, 1300 + 700 * 12 + 200, 970 + 150),
            context));
    return cloudList;
  }

  /**
   * Initialize the background of level3 of the game under this context and return it.
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
