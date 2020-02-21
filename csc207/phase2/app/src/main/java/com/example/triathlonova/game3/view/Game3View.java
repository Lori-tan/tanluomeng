package com.example.triathlonova.game3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.activity.game.Game3Activity;
import com.example.triathlonova.game.view.GameView;
import com.example.triathlonova.game3.presenter.builder.BuilderFactory;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;
import com.example.triathlonova.game3.presenter.processor.Game3Thread;
import com.example.triathlonova.game3.presenter.strategy.Game3Score;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/**
 * The game view class that has the responsibility of making the GameManager class to drawManager
 * and update items through keeping track of thread running. This view class stops the update of
 * view when the thread is stopped.
 */
public class Game3View extends GameView {

  /**
   * A bitmap for the background, grassland for level 1, mountain side for level 2, and snow village
   * for level 3.
   */
  private Bitmap background;
  /** the push sheep manager of this view. */
  private Game3Manager manager;
  /** the level of the game. */
  private String level;
  /** the rect where the background is drawn. */
  private Rect rect;
  /** the push sheep activity that use this view. */
  private Game3Activity activity;
  /** The game3 score tracker. */
  private Game3Score game3Score;

  /**
   * Constructs a new push sheep game view.
   *
   * @param context the environment.
   */
  public Game3View(Context context) {
    super(context);
    getHolder().addCallback(this);
    this.activity = new Game3Activity(); // default
    setGameThread(new Game3Thread(getHolder(), this));
    setFocusable(true);
    this.level = "1"; // default
    initializeBitmaps();
    // set the rect for drawing background
    rect = new Rect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight());
    manager = new Game3Manager((getScreenHeight() / getScale()), (15));
    // based on the level the user choose, build the corresponding map
    BuilderFactory builderFactory = new BuilderFactory();
    manager.setBuilder(builderFactory.makeBuilder(level, manager));
    manager.createGameItems(getContext());
    // use the lives that remains from last game
    manager.setLivesCount(activity.getLivesCount());
    game3Score = new Game3Score(activity.getMyDB(), activity.getUsername(), level, this);
  }

  /**
   * Constructs a new push sheep game view based on the level the user chooses.
   *
   * @param context the environment.
   * @param level the level of the game.
   * @param activity the push sheep activity that use this view.
   */
  public Game3View(Context context, String level, Game3Activity activity) {
    super(context);
    getHolder().addCallback(this);
    this.activity = activity;
    setGameThread(new Game3Thread(getHolder(), this));
    setFocusable(true);
    this.level = level;
    initializeBitmaps();
    // set the rect for drawing background
    rect = new Rect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight());
    manager = new Game3Manager((getScreenHeight() / getScale()), (15));
    // based on the level the user choose, build the corresponding map
    BuilderFactory builderFactory = new BuilderFactory();
    manager.setBuilder(builderFactory.makeBuilder(level, manager));
    manager.createGameItems(getContext());
    // use the lives that remains from last game
    manager.setLivesCount(activity.getLivesCount());
    game3Score = new Game3Score(activity.getMyDB(), activity.getUsername(), level, this);
  }

  /** Initialize the bitmaps that would be used when drawing. */
  private void initializeBitmaps() {
    switch (level) {
      case "1":
        background = BitmapFactory.decodeResource(getResources(), R.drawable.grassland);
        break;
      case "2":
        background = BitmapFactory.decodeResource(getResources(), R.drawable.mountain_side);
        break;
      case "3":
        background = BitmapFactory.decodeResource(getResources(), R.drawable.snow_village);
        break;
    }
  }

  /** Causes this view update its status. */
  @Override
  public void update() {
    if (manager.isGetScore()) {
      addScore();
      manager.setGetScore(false);
    }
    if (!manager.isWin() && manager.isAlive()) {
      manager.updateManager();
    } else if (!manager.isAlive()) {
      getGameThread().setRunning(false);
      activity.backToChooseLevel();
      activity.finish();
    } else {
      getGameThread().setRunning(false);
      activity.win();
    }
    activity.storeLives(manager.getLivesCount());
    activity.showLives();
  }

  /**
   * Draw all the items in this game.
   *
   * @param canvas the canvas on which to draw this view.
   */
  @Override
  public void draw(Canvas canvas) {
    if (canvas != null) {
      super.draw(canvas);
      canvas.drawBitmap(background, null, rect, null);
      manager.drawManager(canvas);
    }
  }

  /**
   * Cause the pusher in the manager to move up, down, left, or right.
   *
   * @param direction the direction of the pusher, must be "up", "down", "left", or "right".
   */
  public void move(String direction) {
    manager.move(direction);
  }

  /** Cause the pusher in the manager to push. */
  public void push() {
    manager.push();
  }

  /** Add the score that the user gains in this game. */
  public void addScore() {
    activity.setChanged();
    activity.notifyObservers();
  }

  /**
   * Return the push sheep manager of this view.
   *
   * @return the push sheep manager of this view.
   */
  public Game3Manager getManager() {
    return manager;
  }

  /**
   * Set the observer to the activity.
   *
   * @param o the observer.
   */
  public void addObserver(ObserverInterface o) {
    activity.addObserver(o);
  }

  /** Show the current score in the screen. */
  public void showScore() {
    activity.showScore();
  }

  /** Get the current score for game 3 */
  public int getScore() {
    return game3Score.getScore();
  }
}
