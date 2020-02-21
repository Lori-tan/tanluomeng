package com.example.triathlonova.game1.model.hero;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game.model.hero.GameHero;
import com.example.triathlonova.game1.model.item.Stairs;
import com.example.triathlonova.game1.presenter.strategy.PressInfo;
import com.example.triathlonova.game1.view.Game1View;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

import java.util.List;

/**
 * A hero that needs to jump up, either left or right, to reach and pick up the gem to win the game.
 */
public class Game1Hero extends GameHero implements ObserverInterface {

  /** The maximum amount of movements allowed by the hero. */
  private final int MAX_VELOCITY = 60;
  /** Indicates whether the hero is jumping to its right. */
  private boolean toRight;
  /** The amount the hero moves in the horizontal direction. */
  private int velocityX;
  /** The amount the hero moves in the vertical direction. */
  private int velocityY;
  /** The natural falling acceleration. */
  private int gravity;
  /** Indicating whether the hero currently stands on stairs. */
  private boolean onSurface;
  /** A list of stairs in this level. */
  private List<Stairs> stairsList;

  /**
   * Initialize this Game1Hero object with the given rectangle under this context.
   *
   * @param rect the rectangle around this hero.
   * @param context the environment.
   */
  public Game1Hero(Rect rect, Context context) {
    super(rect.left / Game1View.getScale(), rect.top / Game1View.getScale(), rect);
    toRight = true;
    onSurface = false;
    setItemPic(BitmapFactory.decodeResource(context.getResources(), R.drawable.knight));
    setRectangle(
        new Rect(
            getX(), getY(), getX() + getItemPic().getWidth(), getY() + getItemPic().getHeight()));
    velocityY = 0;
    velocityX = 0;
    gravity = 3;
    setLivesCount(3);
  }

  /**
   * Set the stairsList variable by injecting a list of Stairs after creation.
   *
   * @param stairsList a list of Stairs object.
   */
  public void setStairsList(List<Stairs> stairsList) {
    this.stairsList = stairsList;
  }

  /**
   * Draw the Game1Hero object onto the screen with the given bitmap image.
   *
   * @param canvas the host on which to draw this item.
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(getItemPic(), getX(), getY(), null);
  }

  /** Causes this hero to take its turn and move. */
  public void update() {
    fall();
  }

  /**
   * Makes the hero fall as long as it doesn't stand on top of a stair. Act accordingly to the law
   * of physics after hitting constraints in any directions.
   */
  private void fall() {
    if (!onSurface) {
      freeFall();
    }
    if (topCollision(stairsList)) {
      standUp();
    }
    if (horizontalCollision(stairsList)) {
      sideBump();
    }
    if (bottomCollision(stairsList)) {
      velocityY = -velocityY;
    }
    if (hitSidesBoundary()) {
      hitSidesReactions();
    }
  }

  /** Let the hero enter object free falling state in physics. */
  private void freeFall() {
    velocityY += gravity;
    setX(getX() + velocityX);
    setY(getY() + velocityY);
    getRectangle()
        .set(getX(), getY(), getX() + getItemPic().getWidth(), getY() + getItemPic().getHeight());
  }

  /**
   * Return true if the hero hits boundaries on the two sides, and false otherwise.
   *
   * @return true iff the x-axis of the hero is at the sides of the screen.
   */
  private boolean hitSidesBoundary() {
    return getX() < 0 || getX() > Game1View.getScreenWidth() - getItemPic().getWidth();
  }

  /** Let the hero react to hitting the sides by bouncing back in the opposite direction. */
  private void hitSidesReactions() {
    if (toRight && velocityX > 0) velocityX = -velocityX;
    else if (!toRight && velocityX < 0) {
      velocityX = -velocityX;
    }
    toRight = !toRight;
  }

  /** Let the hero stands on top of the stairs with stopped motion. */
  private void standUp() {
    onSurface = true;
    velocityY = 0;
    velocityX = 0;
  }

  /** Let the hero bounce in opposite direction when hitting the sides of stairs. */
  private void sideBump() {
    toRight = !toRight;
    velocityX = -velocityX;
  }

  /**
   * Let the hero return to the beginning motion of the game and record that it has lost one life.
   */
  @Override
  protected void newLife() {
    setX(25);
    setY(500);
  }

  /**
   * Take the information on how long the button is pressed to inform the hero to jump by how many
   * units.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  @Override
  public void update(ObservableInterface o, Object arg) {
    PressInfo pressInfo = (PressInfo) arg;
    toRight = pressInfo.isToRight();
    int power = pressInfo.getPressPower();
    if (toRight) jumpRight(power);
    else jumpLeft(power);
  }

  /**
   * Makes the hero jump left by some units that is given by velocity.
   *
   * @param velocity the amount of units the hero needs to move.
   */
  private void jumpLeft(int velocity) {
    toRight = false;
    onSurface = false;

    if (this.velocityY == 0) {
      if (velocity >= MAX_VELOCITY) {
        velocity = MAX_VELOCITY;
      }
      velocityY = -velocity;
      velocityX = -velocity / 3;
    }
  }

  /**
   * Makes the hero jump right by some units that is given by velocity.
   *
   * @param velocity the amount of units the hero needs to move.
   */
  private void jumpRight(int velocity) {
    toRight = true;
    onSurface = false;

    if (this.velocityY == 0) {
      if (velocity >= MAX_VELOCITY) {
        velocity = MAX_VELOCITY;
      }
      velocityY = -velocity;
      velocityX = +velocity / 3;
    }
  }

  /**
   * Return a boolean indicating whether the hero hits any stair from up top.
   *
   * @return true iff the hero steps on top of a stair in stairsList.
   */
  private boolean topCollision(List<Stairs> stairsList) {
    for (Stairs stairs : stairsList) {
      if (stairs.topCollided(this)) return true;
    }
    return false;
  }

  /**
   * Return a boolean indicating whether the hero hits any stair from bottom up.
   *
   * @return true iff the hero hits the bottom of a stair in stairsList.
   */
  private boolean bottomCollision(List<Stairs> stairsList) {
    for (Stairs stairs : stairsList) {
      if (stairs.bottomCollided(this)) return true;
    }
    return false;
  }

  /**
   * Return a boolean indicating whether the hero hits any stair from its sideways.
   *
   * @return true iff the hero hits either the left or the right side of a stair in stairsList.
   */
  private boolean horizontalCollision(List<Stairs> stairsList) {
    for (Stairs stairs : stairsList) {
      if (stairs.horizontallyCollided(this)) return true;
    }
    return false;
  }
}
