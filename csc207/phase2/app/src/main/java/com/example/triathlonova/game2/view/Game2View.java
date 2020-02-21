package com.example.triathlonova.game2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.activity.game.Game2MainActivity;
import com.example.triathlonova.game.view.GameView;
import com.example.triathlonova.game2.presenter.builder.BuilderFactory;
import com.example.triathlonova.game2.presenter.processor.Game2Manager;
import com.example.triathlonova.game2.presenter.processor.Game2Thread;
import com.example.triathlonova.game2.presenter.strategy.Game2Score;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

/**
 * The game view class that has the responsibility of making the GameManager class to drawManager
 * and update items through keeping track of thread running. This view class stops the update of
 * view when the thread is stopped.
 */
public class Game2View extends GameView {

  /** The activity that starts current view. */
  private Game2MainActivity game2MainActivity;
  /** The game contents manager. */
  private Game2Manager game2Manager;
  /** The score for game2. */
  private Game2Score game2Score;

  public Game2View(Context context) {
    super(context);
  }

  /**
   * Creates a new TowerGameView under this context with game2MainActivity initialized by the passed
   * in parameter. Initializes the Game2Manager class to let it start instantiate in-game objects
   * and the Game2Thread class to let it start get track of time and run state.
   *
   * @param context the environment.
   * @param game2Act the activity that is associated with providing the layout of this game.
   */
  public Game2View(Context context, Game2MainActivity game2Act, String level) {
    super(context);
    this.game2MainActivity = game2Act;
    getHolder().addCallback(this);
    game2Manager =
        new Game2Manager(
            (GameView.getScreenHeight() / GameView.getScale()),
            (15),
            new BuilderFactory().constructBuilderOfLevel(level),
            context);
    //     this.game2MainActivity.addObserver(game2Manager.getGame2Hero());
    setGameThread(new Game2Thread(getHolder(), this));
    setFocusable(true);
    setBackground(game2Manager.getBackground());
    setRect(new Rect(0, 0, GameView.getScreenWidth(), GameView.getScreenHeight()));
    game2Manager.setLivesCount(game2MainActivity.getLivesCount());
    game2Score = new Game2Score(game2MainActivity.getMyDB(), game2MainActivity.getUsername(), this);
  }

  /**
   * Make the GameManager class to update the jumper as long as Game2 hasn't been won yet; whenever
   * it has, stop the thread and call the associated activity for this game to start the next game.
   */
  @Override
  public void update() {
    if (game2Manager.isGetScore()) {
      addScore();
      game2Manager.setGetScore(false);
    }
    if (!game2Manager.getHasWon() && game2Manager.getIsAlive()) {
      game2Manager.updateManager();
    } else if (!game2Manager.getIsAlive()) {
      getGameThread().setRunning(false);
      game2MainActivity.backToChooseLevel();
      game2MainActivity.finish();
    } else {
      getGameThread().setRunning(false);
      game2MainActivity.startGame3();
      game2MainActivity.finish();
    }
    game2MainActivity.storeLives(game2Manager.getGame2Hero().getLivesCount());
    game2MainActivity.showLives();
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
      game2Manager.drawManager(canvas);
    }
  }

  /**
   * This sends a signal indicating the player has gained score and tells Game2MainActivity to
   * notify its ObserverInterface(Game2Score)
   */
  public void addScore() {
    game2MainActivity.setChanged();
    game2MainActivity.notifyObservers();
  }

  /**
   * If the flyDownButton is touched, Game2View will get an updateHero() call and tells Game2Manager
   * to let the Game2Hero to sink.
   */
  public void updateHero() {
    game2Manager.getGame2Hero().sinking();
  }

  /**
   * Set the observer to the activity.
   *
   * @param o the observer.
   */
  public void addObserver(ObserverInterface o) {
    game2MainActivity.addObserver(o);
  }

  /** Show the current score in the screen. */
  public void showScore() {
    game2MainActivity.showScore();
  }

  /** Retrieve the score. */
  public int getScore() {
    return game2Score.getScore();
  }
}
