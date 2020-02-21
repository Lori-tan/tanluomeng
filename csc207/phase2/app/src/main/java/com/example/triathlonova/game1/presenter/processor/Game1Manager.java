package com.example.triathlonova.game1.presenter.processor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.triathlonova.game.presenter.processor.GameManager;
import com.example.triathlonova.game1.model.hero.Game1Hero;
import com.example.triathlonova.game1.model.item.Gem;
import com.example.triathlonova.game1.model.item.Hearts;
import com.example.triathlonova.game1.model.item.Stairs;
import com.example.triathlonova.game1.presenter.builder.Game1Builder;
import com.example.triathlonova.game1.view.Game1View;
import com.example.triathlonova.helper.interfaces.ObservableInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A manager of this Game1, which is responsible for sending out orders to different objects,
 * tracking responses and providing updates to the game accordingly.
 */
public class Game1Manager extends GameManager {

  /** A list of stairs in this game. */
  private List<Stairs> stairsList;
  /** The hero in this game. */
  private Game1Hero game1Hero;
  /** A list of gems in this game. */
  private List<Gem> gemList;
  /** A list of gems collected by the user. */
  private List<Gem> collectedGems;
  /** A list of hearts in this game. */
  private List<Hearts> heartsList;
  /** A list of hearts collected the user. */
  private List<Hearts> collectedHearts;
  /** The background image of this game. */
  private Bitmap background;
  /** A game status tracker of this game. */
  private Game1StatusUpdater game1StatusUpdater;

  /**
   * Initialize this Game1Manager with gridWidth width and gridHeight height as the display layout
   * size in the app under this context. The hasWon variable is initialized as false, and sends a
   * call to another private method to initialize other variables of in-game objects.
   *
   * @param gridWidth the width of the screen.
   * @param gridHeight the height of the screen.
   * @param context the environment.
   */
  public Game1Manager(int gridWidth, int gridHeight, Game1Builder game1Builder, Context context) {
    super(gridWidth, gridHeight);
    constructItems(game1Builder, context);
    game1StatusUpdater = new Game1StatusUpdater();
  }

  /**
   * Return the object referred by game1StatusUpdater variable.
   *
   * @return an initialized game1StatusUpdater object.
   */
  public ObservableInterface getGame1StatusUpdater() {
    return game1StatusUpdater;
  }

  /**
   * Call game1Builder to construct items in the game. Set the variables of in-game objects in this
   * class to those three created accordingly.
   *
   * @param game1Builder the builder to create all in-game objects.
   * @param context the environment.
   */
  private void constructItems(Game1Builder game1Builder, Context context) {
    game1Hero = game1Builder.buildGame1Hero(context);
    stairsList = game1Builder.buildStairs(context);
    gemList = game1Builder.buildGem(context);
    heartsList = game1Builder.buildHearts(context);
    collectedHearts = new ArrayList<>();
    collectedGems = new ArrayList<>();
    background = game1Builder.buildBackground(context);
    game1Hero.setStairsList(stairsList);
  }

  /**
   * Return the background image of this game.
   *
   * @return a bitmap image.
   */
  public Bitmap getBackground() {
    return background;
  }

  /**
   * Return the hero in the game; if game1Hero has not been initialized yet, throw a
   * NullPointerException.
   *
   * @return the game1Hero if initialized and exception otherwise.
   */
  public Game1Hero getGame1Hero() {
    if (game1Hero == null) {
      throw new NullPointerException();
    } else {
      return game1Hero;
    }
  }

  /**
   * Make the hero, the stairs in stairsList, the gems in gemList, and the hearts in heartsList to
   * be drawn and shown.
   *
   * @param canvas the host to draw calls displayed onto the screen.
   */
  @Override
  public void drawManager(Canvas canvas) {
    game1Hero.draw(canvas);
    for (Stairs stairs : stairsList) {
      stairs.draw(canvas);
    }
    drawGemsList(canvas);
    drawHeartsList(canvas);
  }

  /**
   * Check whether the player has collected any gems in the game. If yes, remove those from the list
   * of all gems. Then draw the remaining gems in the list if any for display.
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawGemsList(Canvas canvas) {
    if (!collectedGems.isEmpty()) {
      for (Gem collected : collectedGems) {
        gemList.remove(collected);
      }
    }
    collectedGems = new ArrayList<>();
    if (!gemList.isEmpty()) {
      for (Gem gem : gemList) {
        gem.draw(canvas);
      }
    } else {
      game1StatusUpdater.playerWon();
    }
  }

  /**
   * Check whether the player has collected any hearts in the game. If yes, remove those from the
   * list of all hearts. Then draw the remaining hearts in the list if any for display.
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawHeartsList(Canvas canvas) {
    if (!collectedHearts.isEmpty()) {
      for (Hearts collected : collectedHearts) {
        heartsList.remove(collected);
      }
    }
    collectedHearts = new ArrayList<>();
    if (!heartsList.isEmpty()) {
      for (Hearts hearts : heartsList) {
        hearts.draw(canvas);
      }
    }
  }

  /**
   * Make the character in this game to take its turn and give an action according to the specific
   * functionality of the game1Hero. Detect whether the gem has been collected by the game1Hero
   * after its most recent move; if yes, record the player has won the game.
   */
  @Override
  public void updateManager() {
    game1Hero.update();
    if (!heartsList.isEmpty()) {
      for (Hearts hearts : heartsList) {
        if (hearts.collidedWith(game1Hero.getRectangle())) {
          game1Hero.logLives("+");
          collectedHearts.add(hearts);
        }
      }
    }
    if (game1Hero.getY() >= Game1View.getScreenHeight() - 10) {
      if (game1Hero.lastLife()) {
        game1StatusUpdater.playerDied();
      } else {
        game1Hero.logLives("-");
      }
    }
    if (gemList.get(0).collidedWith(game1Hero.getRectangle())) {
      collectedGems.add(gemList.get(0));
      game1StatusUpdater.playerWon();
    }
  }

  /**
   * Return how many life the jumper in this manager has left.
   *
   * @return an int of the amount of life of the jumper in this manager.
   */
  public int getLivesCount() {
    return game1Hero.getLivesCount();
  }
}
