package com.example.triathlonova.game2.presenter.processor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.triathlonova.game.presenter.processor.GameManager;
import com.example.triathlonova.game2.model.hero.Game2Hero;
import com.example.triathlonova.game2.model.item.Building;
import com.example.triathlonova.game2.model.item.Cloud;
import com.example.triathlonova.game2.model.item.Gem;
import com.example.triathlonova.game2.model.item.Lightning;
import com.example.triathlonova.game2.presenter.builder.Game2Builder;

import java.util.List;

/**
 * A manager of Hot Air Balloon game, which is responsible for sending out orders to different
 * objects, tracking responses and providing updates to the game accordingly.
 */
public class Game2Manager extends GameManager {

  /** The character in this game: an Hot Air Balloon. */
  private Game2Hero game2Hero;

  /** local fields for Gems, Lightnings, Buildings, and Clouds. */
  private List<Gem> gemList;

  private List<Lightning> lightningList;
  private List<Building> buildingList;
  private List<Cloud> cloudList;
  /** Indicating whether the player has won the game. */
  private boolean hasWon;
  /** Indicating the current status of the player. */
  private boolean isAlive;
  /** The background image of this game. */
  private Bitmap background;
  /** Indicating whether the player gains score */
  private boolean getScore;

  /**
   * Initialize this Game2Manager with gridWidth width and gridHeight height as the display layout
   * size in the app under this context. The hasWon variable is initialized as false, and sends a
   * call to another private method to initialize other variables of in-game objects.
   *
   * @param gridWidth the width of the screen.
   * @param gridHeight the height of the screen.
   * @param context the environment.
   */
  public Game2Manager(int gridWidth, int gridHeight, Game2Builder game2Builder, Context context) {
    super(gridWidth, gridHeight);
    isAlive = true;
    constructItems(game2Builder, context);
  }

  /**
   * Call game2Builder to construct a game2Hero, a list of gems, a list of lightnings, a list of
   * buildings, a list of clouds, and a background. Set the variables of in-game objects in this
   * class to those three created accordingly.
   *
   * @param game2Builder the builder to create all in-game objects.
   * @param context the environment.
   */
  private void constructItems(Game2Builder game2Builder, Context context) {
    game2Hero = game2Builder.buildGame2Hero(context);
    gemList = game2Builder.buildGems(context);
    lightningList = game2Builder.buildLightning(context);
    buildingList = game2Builder.buildBuilding(context);
    cloudList = game2Builder.buildCloud(context);
    background = game2Builder.buildBackground(context);
  }

  /**
   * Return whether the player has won the game after its move.
   *
   * @return the value of hasWon.
   */
  public boolean getHasWon() {
    return hasWon;
  }

  /**
   * Return whether the player has lives left.
   *
   * @return the value of hasWon.
   */
  public boolean getIsAlive() {
    return isAlive;
  }

  public Bitmap getBackground() {
    return background;
  }

  public Game2Hero getGame2Hero() {
    return game2Hero;
  }

  /**
   * Make the character game2Hero, the stairs in stairsList, the gems in gemList, and the hearts in
   * heartsList to be drawn and shown.
   *
   * @param canvas the host to draw calls displayed onto the screen.
   */
  @Override
  public void drawManager(Canvas canvas) {
    game2Hero.draw(canvas);
    drawGemList(canvas);
    drawLightningList(canvas);
    drawBuildingList(canvas);
    drawCloudList(canvas);
  }

  /**
   * draw gems on the canvas
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawGemList(Canvas canvas) {
    if (!gemList.isEmpty()) {
      for (Gem gem : gemList) {
        gem.draw(canvas);
      }
    }
  }

  /**
   * draw lightnings on the canvas
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawLightningList(Canvas canvas) {
    if (!lightningList.isEmpty()) {
      for (Lightning lightning : lightningList) {
        lightning.draw(canvas);
      }
    }
  }

  /**
   * draw buildings on the canvas
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawBuildingList(Canvas canvas) {
    if (!buildingList.isEmpty()) {
      for (Building building : buildingList) {
        building.draw(canvas);
      }
    }
  }

  /**
   * draw clouds on the canvas
   *
   * @param canvas the host to which draw calls displayed onto the screen.
   */
  private void drawCloudList(Canvas canvas) {
    if (!cloudList.isEmpty()) {
      for (Cloud cloud : cloudList) {
        cloud.draw(canvas);
      }
    }
  }

  /**
   * Make the character in this game to take its turn and give an action according to the specific
   * functionality of the game2Hero. Detect whether the last gem has been collected by the game2Hero
   * after its most recent move; if yes, record the player has won the game. Detect whether any
   * lethal collision happens: if yes, lose a life if player has more than 1 lives or lose the game
   * if the player only has 1 life; if no, keep going.
   */
  @Override
  public void updateManager() {
    game2Hero.update();
    for (Gem gem : gemList) {
      gem.update();
    }
    for (Lightning lightning : lightningList) {
      lightning.update();
    }
    for (Building building : buildingList) {
      building.update();
    }
    for (Cloud cloud : cloudList) {
      cloud.update();
    }
    if (checkLethalCollision()) {
      if (game2Hero.getLivesCount() > 1) {
        game2Hero.logLives("-");
      } else if (game2Hero.getLivesCount() == 1) {
        gameOver();
      }
    }
    if (checkGemCollision()) {
      setGetScore(true);
      if (gemList.get(gemList.size() - 1).getX() < 0) {
        gameWon();
      }
    }
  }

  /**
   * Check if the game2Hero is collided with any obstacle.
   *
   * @return true if collided. false if not collided.
   */
  private boolean checkLethalCollision() {
    return checkLightningCollision() || checkBuildingCollision() || checkCloudCollision();
  }

  /** helper method for checkLethalCollision */
  private boolean checkLightningCollision() {
    boolean collided = false;
    for (Lightning lightning : lightningList) {
      if (game2Hero.collidedWith(lightning.getRectangle())) {
        collided = true;
        lightning.remove();
      }
    }
    return collided;
  }

  /** helper method for checkLethalCollision */
  private boolean checkBuildingCollision() {
    boolean collided = false;
    for (Building building : buildingList) {
      if (game2Hero.collidedWith(building.getRectangle())) {
        collided = true;
        building.remove();
      }
    }
    return collided;
  }

  /** helper method for checkLethalCollision */
  private boolean checkCloudCollision() {
    boolean collided = false;
    for (Cloud cloud : cloudList) {
      if (game2Hero.collidedWith(cloud.getRectangle())) {
        collided = true;
        cloud.remove();
      }
    }
    return collided;
  }

  /**
   * Check if the game2Hero is collided with any collectible gems.
   *
   * @return true if collided. false if not collided.
   */
  private boolean checkGemCollision() {
    boolean collided = false;
    for (Gem gem : gemList) {
      if (game2Hero.collidedWith(gem.getRectangle())) {
        collided = true;
        gem.remove();
      }
    }
    return collided;
  }

  /** @return whether the player gains score */
  public boolean isGetScore() {
    return this.getScore;
  }

  /**
   * set parameter getScore to local field getScore
   *
   * @param getScore indicating whether the player gain score
   */
  public void setGetScore(boolean getScore) {
    this.getScore = getScore;
  }

  /**
   * set parameter livesCount to local field livesCount
   *
   * @param livesCount indicating how many lives the player has left
   */
  public void setLivesCount(int livesCount) {
    game2Hero.setLivesCount(livesCount);
  }

  /** Change the isAlive variable to signal that the player has died. */
  private void gameOver() {
    isAlive = false;
  }

  /** Change the hasWon variable to signal that the player has won the game. */
  private void gameWon() {
    hasWon = true;
  }
}
