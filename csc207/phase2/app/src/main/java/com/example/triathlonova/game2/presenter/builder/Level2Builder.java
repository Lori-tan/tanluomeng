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
 * Level2Builder for game2: this level initiates a game with length of approximately 7500 pixels
 * units. It has 9 gems with gaps of 600 units, 17 lightnings with gaps of 400 units, 8 buildings
 * with gaps of 600 units, and 9 clouds with gaps of 700 units.
 */
public class Level2Builder extends GameBuilder implements Game2Builder {

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
   * Initialize some gems (based on level2 map) under this context and return a list containing all
   * of the constructed gems.
   *
   * @param context the environment.
   * @return a constructed list of initialized gems.
   */
  @Override
  public List<Gem> buildGems(Context context) {
    List<Gem> gemList = new ArrayList<>();
    gemList.add(new Gem(1200, 650, false, new Rect(1200, 650, 1200 + 100, 650 + 100), context));
    gemList.add(
        new Gem(
            1200 + 800,
            970,
            false,
            new Rect(1200 + 800, 970, 1200 + 800 + 100, 970 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 2,
            1090,
            false,
            new Rect(1200 + 800 * 2, 890, 1200 + 800 * 2 + 100, 890 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 3,
            710,
            false,
            new Rect(1200 + 800 * 3, 710, 1200 + 800 * 3 + 100, 710 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 4,
            1150,
            false,
            new Rect(1200 + 800 * 4, 850, 1200 + 800 * 4 + 100, 850 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 5,
            620,
            false,
            new Rect(1200 + 800 * 5, 620, 1200 + 800 * 5 + 100, 620 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 6,
            1030,
            false,
            new Rect(1200 + 800 * 6, 830, 1200 + 800 * 6 + 100, 830 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 7,
            1270,
            false,
            new Rect(1200 + 800 * 7, 770, 1200 + 800 * 7 + 100, 770 + 100),
            context));
    gemList.add(
        new Gem(
            1200 + 800 * 8,
            0,
            true,
            new Rect(1200 + 800 * 8, 0, 1200 + 800 * 8 + 252, 2160),
            context));

    return gemList;
  }

  /**
   * Initialize some lightnings (based on level2 map) under this context and return a list
   * containing all of the constructed lightnings.
   *
   * @param context the environment.
   * @return a constructed list of initialized lightning.
   */
  @Override
  public List<Lightning> buildLightning(Context context) {
    List<Lightning> lightningList = new ArrayList<>();
    lightningList.add(new Lightning(1000, 90, new Rect(1000, 90, 1000 + 250, 90 + 500), context));

    lightningList.add(
        new Lightning(
            1000 + 400, 100, new Rect(1000 + 400, 100, 1000 + 400 + 250, 100 + 500), context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 2,
            80,
            new Rect(1000 + 400 * 2, 80, 1000 + 400 * 2 + 250, 80 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 3,
            120,
            new Rect(1000 + 400 * 3, 120, 1000 + 400 * 3 + 250, 120 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 4,
            110,
            new Rect(1000 + 400 * 4, 110, 1000 + 400 * 4 + 250, 110 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 5,
            100,
            new Rect(1000 + 400 * 5, 100, 1000 + 400 * 5 + 250, 100 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 6,
            150,
            new Rect(1000 + 400 * 6, 150, 1000 + 400 * 6 + 250, 150 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 7,
            120,
            new Rect(1000 + 400 * 7, 120, 1000 + 400 * 7 + 250, 120 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 8,
            130,
            new Rect(1000 + 400 * 8, 130, 1000 + 400 * 8 + 250, 130 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 9,
            90,
            new Rect(1000 + 400 * 9, 90, 1000 + 400 * 9 + 250, 90 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 10,
            140,
            new Rect(1000 + 400 * 10, 140, 1000 + 400 * 10 + 250, 140 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 11,
            110,
            new Rect(1000 + 400 * 11, 110, 1000 + 400 * 11 + 250, 110 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 12,
            130,
            new Rect(1000 + 400 * 12, 130, 1000 + 400 * 12 + 250, 130 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 13,
            90,
            new Rect(1000 + 400 * 13, 90, 1000 + 400 * 13 + 250, 90 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 14,
            140,
            new Rect(1000 + 400 * 14, 140, 1000 + 400 * 14 + 250, 140 + 500),
            context));
    lightningList.add(
        new Lightning(
            1000 + 400 * 15,
            120,
            new Rect(1000 + 400 * 15, 120, 1000 + 400 * 15 + 250, 120 + 500),
            context));

    lightningList.add(
        new Lightning(
            1000 + 400 * 16,
            150,
            new Rect(1000 + 400 * 16, 150, 1000 + 400 * 16 + 250, 150 + 500),
            context));
    return lightningList;
  }

  /**
   * Initialize some buildings (based on level2 map) under this context and return a list containing
   * all of the constructed buildings.
   *
   * @param context the environment.
   * @return a constructed list of initialized buildings.
   */
  @Override
  public List<Building> buildBuilding(Context context) {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(
        new Building(5, 1100, 2100 - 600, new Rect(1100, 2100 - 600, 1100 + 430, 2100), context));
    buildingList.add(
        new Building(
            6,
            1100 + 900,
            2100 - 600,
            new Rect(1100 + 900, 2100 - 600, 1100 + 900 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            2,
            1100 + 900 * 2,
            2100 - 600,
            new Rect(1100 + 900 * 2, 2100 - 600, 1100 + 900 * 2 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            3,
            1100 + 900 * 3,
            2100 - 600,
            new Rect(1100 + 900 * 3, 2100 - 600, 1100 + 900 * 3 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            4,
            1100 + 900 * 4,
            2100 - 600,
            new Rect(1100 + 900 * 4, 2100 - 600, 1100 + 900 * 4 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            1,
            1100 + 900 * 5,
            2100 - 600,
            new Rect(1100 + 900 * 5, 2100 - 600, 1100 + 900 * 5 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            3,
            1100 + 900 * 6,
            2100 - 600,
            new Rect(1100 + 900 * 6, 2100 - 600, 1100 + 900 * 6 + 430, 2100),
            context));
    buildingList.add(
        new Building(
            5,
            1100 + 900 * 7,
            2100 - 600,
            new Rect(1100 + 900 * 7, 2100 - 600, 1100 + 900 * 7 + 430, 2100),
            context));
    return buildingList;
  }

  /**
   * Initialize some clouds (based on level2 map) under this context and return a list containing
   * all of the constructed clouds.
   *
   * @param context the environment.
   * @return a constructed list of initialized clouds.
   */
  @Override
  public List<Cloud> buildCloud(Context context) {
    List<Cloud> cloudList = new ArrayList<>();
    cloudList.add(new Cloud(2, 1300, 800, new Rect(1300, 800, 1300 + 200, 800 + 150), context));
    cloudList.add(
        new Cloud(
            4,
            1300 + 700,
            1200,
            new Rect(1300 + 700, 1200, 1300 + 700 + 200, 1200 + 150),
            context));
    cloudList.add(
        new Cloud(
            1,
            1300 + 700 * 2,
            750,
            new Rect(1300 + 700 * 2, 750, 1300 + 700 * 2 + 200, 750 + 150),
            context));
    cloudList.add(
        new Cloud(
            3,
            1300 + 700 * 3,
            1230,
            new Rect(1300 + 700 * 3, 1230, 1300 + 700 * 3 + 200, 1230 + 150),
            context));
    cloudList.add(
        new Cloud(
            6,
            1300 + 700 * 4,
            860,
            new Rect(1300 + 700 * 4, 860, 1300 + 700 * 4 + 200, 860 + 150),
            context));
    cloudList.add(
        new Cloud(
            5,
            1300 + 700 * 5,
            1100,
            new Rect(1300 + 700 * 5, 1100, 1300 + 700 * 5 + 200, 1100 + 150),
            context));
    cloudList.add(
        new Cloud(
            2,
            1300 + 700 * 6,
            870,
            new Rect(1300 + 700 * 6, 870, 1300 + 700 * 6 + 200, 870 + 150),
            context));
    cloudList.add(
        new Cloud(
            4,
            1300 + 700 * 7,
            990,
            new Rect(1300 + 700 * 7, 990, 1300 + 700 * 7 + 200, 990 + 150),
            context));
    cloudList.add(
        new Cloud(
            3,
            1300 + 700 * 8,
            1080,
            new Rect(1300 + 700 * 8, 1080, 1300 + 700 * 8 + 200, 1080 + 150),
            context));
    return cloudList;
  }

  /**
   * Initialize the background of level2 of the game under this context and return it.
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
