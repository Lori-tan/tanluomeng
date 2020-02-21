package com.example.triathlonova.game3.presenter.builder;

import android.content.Context;
import android.graphics.Rect;

import com.example.triathlonova.game3.model.hero.Sheep;
import com.example.triathlonova.game3.model.hero.SheepPusher;
import com.example.triathlonova.game3.model.item.Sewer;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;
import com.example.triathlonova.game3.view.Game3View;

import java.util.ArrayList;

/** A level one builder that constructs the elements of a push sheep manager. */
public class Level1Builder implements Game3Builder {
  /** the push sheep manager where the builder is used. */
  private Game3Manager manager;
  /** the scale of the manager */
  private int scale = Game3View.getScale();

  /**
   * Constructs a new level one builder.
   *
   * @param manager the push sheep manager where the builder is used.
   */
  Level1Builder(Game3Manager manager) {
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
        new Sheep(new Rect(3 * scale, 3 * scale, 4 * scale, 4 * scale), manager, context, "1"));
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
    sewers.add(new Sewer(new Rect(14 * scale, 0, 15 * scale, scale), context));
    sewers.add(new Sewer(new Rect(14 * scale, 25 * scale, 15 * scale, 28 * scale), context));
    sewers.add(new Sewer(new Rect(0, 25 * scale, scale, 28 * scale), context));
    sewers.add(new Sewer(new Rect(8 * scale, 20 * scale, 10 * scale, 21 * scale), context));
    sewers.add(new Sewer(new Rect(3 * scale, 15 * scale, 4 * scale, 19 * scale), context));
    sewers.add(new Sewer(new Rect(2 * scale, 6 * scale, 5 * scale, 7 * scale), context));
    manager.setSewers(sewers);
  }
}
