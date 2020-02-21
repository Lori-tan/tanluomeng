package com.example.triathlonova.game3.presenter.processor;

import android.content.Context;
import android.graphics.Canvas;

import com.example.triathlonova.game.presenter.processor.GameManager;
import com.example.triathlonova.game3.model.hero.Game3Hero;
import com.example.triathlonova.game3.model.hero.Sheep;
import com.example.triathlonova.game3.model.hero.SheepPusher;
import com.example.triathlonova.game3.model.item.Sewer;
import com.example.triathlonova.game3.presenter.builder.Game3Builder;

import java.util.ArrayList;

/**
 * A manager of this PushSheep game, which is responsible for sending out orders to different
 * objects, tracking responses and providing updates to the game accordingly.
 */
public class Game3Manager extends GameManager {

  /** the builder that builds the items in this manager. */
  private Game3Builder builder;
  /** All PushSheepPlayers in this manager, involving sheep pusher and sheep. */
  private ArrayList<Game3Hero> involvers = new ArrayList<>();
  /** the sheep pusher in this manager. */
  private SheepPusher pusher;
  /** All sewers in this manager. */
  private ArrayList<Sewer> sewers;
  /** All sheep in this manager. */
  private ArrayList<Sheep> sheep;
  /** Indicates whether the user wins. */
  private boolean win = false;
  /** number of sheep the user pushes down the sewer in this game. */
  private int count = 0;
  /** number of sheep the user need to pushes down the sewer in order to win. */
  private int goal;
  /** Indicates whether the pusher is alive. */
  private boolean alive = true;
  /** A sheep that need to be removed. */
  private Sheep sheepToRemove;
  /** indicates whether or not the user get score. */
  private boolean getScore = false;

  /**
   * Create the involover ArrayList that contain Sheep and SheepPusher.
   *
   * @param height the height of the manager
   * @param width the width of the manager
   */
  public Game3Manager(int height, int width) {
    super(width, height);
  }

  /**
   * Return the list of the involvers in this manager.
   *
   * @return the list of the involvers in this manager.
   */
  public ArrayList<Game3Hero> getInvolvers() {
    return involvers;
  }

  /**
   * Set the number of sheep the user need to pushes down the sewer in order to win.
   *
   * @param goal the number of sheep the user need to pushes down the sewer in order to win.
   */
  public void setGoal(int goal) {
    this.goal = goal;
  }

  /**
   * Set the builder for this manager.
   *
   * @param builder the builder for this manager.
   */
  public void setBuilder(Game3Builder builder) {
    this.builder = builder;
  }

  /**
   * Set the sheep pusher in this manager.
   *
   * @param pusher the sheep pusher in this manager.
   */
  public void setPusher(SheepPusher pusher) {
    this.pusher = pusher;
    involvers.add(pusher);
  }

  /**
   * Return the list of sheep in this manager.
   *
   * @return the list of sheep in this manager.
   */
  public ArrayList<Sewer> getSewers() {
    return sewers;
  }

  /**
   * Set the list of sewers in this manager.
   *
   * @param sewers the list of sewers in this manager.
   */
  public void setSewers(ArrayList<Sewer> sewers) {
    this.sewers = sewers;
  }

  /**
   * Return the list of sewers in this manager.
   *
   * @return the list of sewers in this manager.
   */
  public ArrayList<Sheep> getSheep() {
    return sheep;
  }

  /**
   * Set the list of sheep in this manager.
   *
   * @param sheep the list of sheep in this manager.
   */
  public void setSheep(ArrayList<Sheep> sheep) {
    this.sheep = sheep;
    involvers.addAll(sheep);
  }

  /**
   * Remove the sheep from the list of involvers in this manager.
   *
   * @param sheep the sheep from the list of involvers in this manager that should be removed.
   */
  public void removeSheep(Sheep sheep) {
    sheepToRemove = sheep;
  }

  /** Add 1 count of sheep that falls down into the sewer in this game. */
  public void addCount() {
    count += 1;
  }

  /**
   * Return a boolean indicates whether or not the user get score.
   *
   * @return a boolean indicates whether or not the user get score.
   */
  public boolean isGetScore() {
    return this.getScore;
  }

  /**
   * Set the boolean indicates whether or not the user get score.
   *
   * @param getScore a boolean indicates whether or not the user get score.
   */
  public void setGetScore(boolean getScore) {
    this.getScore = getScore;
  }

  /**
   * Return whether or not the user wins.
   *
   * @return a boolean indicates whether or not the user wins.
   */
  public boolean isWin() {
    return win;
  }

  /**
   * Return whether or not the pusher is alive.
   *
   * @return a boolean indicates whether or not the pusher is alive.
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Set the status of whether or not the pusher is alive.
   *
   * @param alive a boolean indicates whether or not the pusher is alive.
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  /** Update the items and players in this manager. */
  @Override
  public void updateManager() {
    for (Game3Hero pushable : involvers) {
      pushable.update();
    }
    involvers.remove(sheepToRemove);
    sheepToRemove = null;
    if (count == goal) {
      win = true;
    }
  }

  /**
   * Draw all items and players in this manager.
   *
   * @param canvas the host on which to draw the object.
   */
  @Override
  public void drawManager(Canvas canvas) {
    pusher.draw(canvas);
    for (Game3Hero pushable : involvers) {
      pushable.draw(canvas);
    }
    for (Sewer sewer : sewers) {
      sewer.draw(canvas);
    }
  }

  /**
   * Initialize sheep pusher, sheep, and sewers constructed under this context.
   *
   * @param context the environment.
   */
  public void createGameItems(Context context) {
    builder.buildPusher(context);
    builder.buildSewers(context);
    builder.buildSheep(context);
  }

  /**
   * Cause the pusher in the manager to move up, down, left, or right.
   *
   * @param direction the direction of the pusher, must be "up", "down", "left", or "right".
   */
  public void move(String direction) {
    switch (direction) {
      case "up":
        pusher.goUp();
        break;
      case "down":
        pusher.goDown();
        break;
      case "left":
        pusher.goLeft();
        break;
      case "right":
        pusher.goRight();
        break;
    }
  }

  /** Cause the pusher in the manager to push. */
  public void push() {
    pusher.push();
  }

  /**
   * Return how many life the pusher in this manager has left.
   *
   * @return an int of the amount of life of the pusher in this manager.
   */
  public int getLivesCount() {
    return pusher.getLivesCount();
  }

  /**
   * set amount of lives of the pusher in this manager to livesCount.
   *
   * @param livesCount amount of lives of the pusher in this manager.
   */
  public void setLivesCount(int livesCount) {
    pusher.setLivesCount(livesCount);
  }
}
