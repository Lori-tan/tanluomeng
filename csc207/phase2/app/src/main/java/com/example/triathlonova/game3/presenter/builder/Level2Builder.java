package com.example.triathlonova.game3.presenter.builder;

import android.content.Context;
import android.graphics.Rect;

import com.example.triathlonova.game3.model.hero.Sheep;
import com.example.triathlonova.game3.model.hero.SheepPusher;
import com.example.triathlonova.game3.model.item.Sewer;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;
import com.example.triathlonova.game3.view.Game3View;

import java.util.ArrayList;

/** A level two builder that constructs the elements of a push sheep manager. */
public class Level2Builder implements Game3Builder {
  /** the push sheep manager where the builder is used. */
  private Game3Manager manager;
  /** the scale of the manager */
  private int scale = Game3View.getScale();

  /**
   * Constructs a new level two builder.
   *
   * @param manager the push sheep manager where the builder is used.
   */
  Level2Builder(Game3Manager manager) {
    this.manager = manager;
  }

  /**
   * Construct the sheep pusher in the manager.
   *
   * @param context the environment
   */
  @Override
  public void buildPusher(Context context) {
    manager.setPusher(
        new SheepPusher(new Rect(2 * scale, 2 * scale, 3 * scale, 3 * scale), manager, context));
  }

  /**
   * Construct lots of sheep in the manager.
   *
   * @param context the environment
   */
  @Override
  public void buildSheep(Context context) {
    ArrayList<Sheep> sheeps = new ArrayList<>();
    sheeps.add(
        new Sheep(new Rect(3 * scale, 3 * scale, 4 * scale, 4 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(6 * scale, 6 * scale, 7 * scale, 7 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(4 * scale, 12 * scale, 5 * scale, 13 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(7 * scale, 16 * scale, 8 * scale, 17 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(8 * scale, 18 * scale, 9 * scale, 19 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(13 * scale, 12 * scale, 14 * scale, 13 * scale), manager, context, "2"));

    sheeps.add(
        new Sheep(new Rect(4 * scale, 16 * scale, 5 * scale, 17 * scale), manager, context, "2"));

    manager.setSheep(sheeps);
    manager.setGoal(sheeps.size());
  }

  /**
   * Construct lots of sewers in the manager.
   *
   * @param context the environment
   */
  @Override
  public void buildSewers(Context context) {
    ArrayList<Sewer> sewers = new ArrayList<>();
    sewers.add(new Sewer(new Rect(0, 0, scale, scale), context));
    sewers.add(new Sewer(new Rect(0, 4 * scale, scale, 5 * scale), context));
    sewers.add(new Sewer(new Rect(14 * scale, 4 * scale, 15 * scale, 5 * scale), context));
    sewers.add(new Sewer(new Rect(14 * scale, 0, 15 * scale, scale), context));
    sewers.add(new Sewer(new Rect(14 * scale, 25 * scale, 15 * scale, 28 * scale), context));
    sewers.add(new Sewer(new Rect(0, 25 * scale, scale, 28 * scale), context));
    sewers.add(new Sewer(new Rect(5 * scale, 20 * scale, 10 * scale, 21 * scale), context));
    sewers.add(new Sewer(new Rect(5 * scale, 15 * scale, 6 * scale, 20 * scale), context));
    sewers.add(new Sewer(new Rect(5 * scale, 15 * scale, 10 * scale, 16 * scale), context));
    sewers.add(new Sewer(new Rect(9 * scale, 17 * scale, 10 * scale, 21 * scale), context));
    manager.setSewers(sewers);
  }
}
