package com.example.triathlonova.game1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.activity.game.Game1Activity;
import com.example.triathlonova.game.view.GameView;
import com.example.triathlonova.game1.presenter.builder.BuilderFactory;
import com.example.triathlonova.game1.presenter.processor.Game1Manager;
import com.example.triathlonova.game1.presenter.processor.Game1Thread;
import com.example.triathlonova.game1.presenter.strategy.Game1Score;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/** A class that makes the GameManager class to draw and update items. */
public class Game1View extends GameView implements ObserverInterface {

  /** The activity that starts current view. */
  private Game1Activity game1Activity;
  /** The game contents manager. */
  private Game1Manager game1Manager;
  /** The score for game1. */
  private Game1Score game1Score;

  public Game1View(Context context) {
    super(context);
  }

  /**
   * Creates a new TowerGameView under this context with game1Activity initialized by the passed in
   * parameter. Initializes the Game1Manager class to let it start instantiate in-game objects and
   * the Game1Thread class to let it start get track of time and run state.
   *
   * @param context the environment.
   * @param game1Activity the activity that is associated with providing the layout of this game.
   */
  public Game1View(Context context, Game1Activity game1Activity, String level) {
    super(context);
    this.game1Activity = game1Activity;
    getHolder().addCallback(this);
    game1Manager =
        new Game1Manager(
            (GameView.getScreenHeight() / GameView.getScale()),
            (15),
            new BuilderFactory().constructBuilderOfLevel(level),
            context);
    game1Manager.getGame1StatusUpdater().addObserver(this);
    game1Activity.addObserver(game1Manager.getGame1Hero());
    setGameThread(new Game1Thread(getHolder(), this));
    setFocusable(true);
    setBackground(game1Manager.getBackground());
    setRect(new Rect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight()));
    game1Score = new Game1Score(game1Activity.getMyDB(), game1Activity.getUsername(), this);
  }

  /**
   * Make the GameManager class to update the jumper as long as Game1 hasn't been won yet; whenever
   * it has, stop the thread and call the associated activity for this game to start the next game.
   */
  @Override
  public void update() {
    game1Manager.updateManager();
    game1Activity.storeLives(game1Manager.getLivesCount());
    game1Activity.showLives();
  }

  /**
   * Display the background image of the game and call the GameManager class to drawManager in-game
   * objects.
   *
   * @param canvas the host on which to drawManager the objects.
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawBitmap(getThisBackground(), null, getRect(), null);
      game1Manager.drawManager(canvas);
    }
  }

  /**
   * Set the displayed activity by acting accordingly to what the message passed in was.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  @Override
  public void update(ObservableInterface o, Object arg) {
    String message = (String) arg;
    getGameThread().setRunning(false);
    if (message.equals("won")) {
      game1Activity.startGame2();
      game1Activity.finish();
    } else {
      game1Activity.backToChooseLevel();
      game1Activity.finish();
    }
  }

  /**
   * Set the observer to the activity.
   *
   * @param o the observer.
   */
  public void addObserver(ObserverInterface o) {
    game1Activity.addObserver(o);
  }

  /** Show the current score in the screen. */
  public void showScore() {
    game1Activity.showScore();
  }

  public int getScore() {
    return game1Score.getScore();
  }
}
