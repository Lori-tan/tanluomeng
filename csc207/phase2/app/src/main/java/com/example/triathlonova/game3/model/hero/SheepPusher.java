package com.example.triathlonova.game3.model.hero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;
import com.example.triathlonova.game3.view.Game3View;

/** A sheep pusher that can move up, down, left and right and can push a sheep. */
public class SheepPusher extends Game3Hero {

  /** A bitmap for the waling to the right posture of the pusher. */
  private Bitmap moveRight;
  /** A bitmap for the waling up posture of the pusher. */
  private Bitmap moveUp;
  /** A bitmap for the waling to the left posture of the pusher. */
  private Bitmap moveLeft;
  /** A bitmap for the waling down posture of the pusher. */
  private Bitmap moveDown;
  /** A bitmap for water gun when the pusher pushes to the right. */
  private Bitmap rightWaterGun;
  /** A bitmap for water gun when the pusher pushes to the left. */
  private Bitmap leftWaterGun;
  /** A bitmap for water gun when the pusher pushes up. */
  private Bitmap upWaterGun;
  /** A bitmap for water gun when the pusher pushes down. */
  private Bitmap downWaterGun;
  /** Scale of the manager. */
  private int scale;
  /** Indicates whether this pusher is moving up. */
  private boolean goingUp;
  /** Indicates whether this pusher is moving down. */
  private boolean goingDown;
  /** Indicates whether this pusher is moving left. */
  private boolean goingLeft;
  /** Indicates whether this pusher is moving right. */
  private boolean goingRight;
  /** Indicates whether this pusher is pushing. */
  private boolean pushed = false;

  /**
   * Constructs a new sheep pusher.
   *
   * @param rec the rectangle that represent this sheep.
   * @param manager the push sheep manager that this sheep is in.
   * @param context the environment.
   */
  public SheepPusher(Rect rec, Game3Manager manager, Context context) {
    super(rec, manager);
    setDirection("down");
    scale = Game3View.getScale();
    initializeBitmaps(context);
  }

  /**
   * Set the direction for the pusher. It could be up, down, left, or right.
   *
   * @param direction the direction of the pusher, must be "up", "down", "left", or "right".
   */
  private void setDirection(String direction) {
    goingUp = false;
    goingDown = false;
    goingLeft = false;
    goingRight = false;
    switch (direction) {
      case "up":
        goingUp = true;
        break;
      case "down":
        goingDown = true;
        break;
      case "left":
        goingLeft = true;
        break;
      case "right":
        goingRight = true;
        break;
    }
  }

  /**
   * Initialize the bitmaps that would be used when drawing.
   *
   * @param context the environment
   */
  private void initializeBitmaps(Context context) {
    moveRight = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right);
    moveLeft = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left);
    moveUp = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_up);
    moveDown = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_down);
    rightWaterGun =
        BitmapFactory.decodeResource(context.getResources(), R.drawable.right_water_gun);
    leftWaterGun = BitmapFactory.decodeResource(context.getResources(), R.drawable.left_water_gun);
    upWaterGun = BitmapFactory.decodeResource(context.getResources(), R.drawable.up_water_gun);
    downWaterGun = BitmapFactory.decodeResource(context.getResources(), R.drawable.down_water_gun);
  }

  /**
   * The helper function of the push function, which is used to check if the sheep could be pushed.
   * A sheep can be pushed only when the sheep is in front of the pusher either 1 or 2 distance
   * away.
   *
   * @param poorSheep the sheep that is gonna to be pushed
   */
  private boolean pushable(Sheep poorSheep) {
    int x = getX();
    int y = getY();
    int sheepX = poorSheep.getX();
    int sheepY = poorSheep.getY();
    if (goingRight) {
      return (x == sheepX - 2 || x == sheepX - 1) && (y == sheepY);
    } else if (goingLeft) {
      return (x == sheepX + 2 || x == sheepX + 1) && (y == sheepY);
    } else if (goingUp) {
      return (y == sheepY + 2 || y == sheepY + 1) && (x == sheepX);
    } else if (goingDown) {
      return (y == sheepY - 2 || y == sheepY - 1) && (x == sheepX);
    } else {
      return false;
    }
  }

  /**
   * The sheep pusher pushes. Would always lead to animation even though there might be nothing in
   * front of the SheepPusher. If the sheep is 1 distance away from the pusher, it would be pushed
   * twice, each time with 1 distance away, so 2 distance away in total. If the sheep is 2 distance
   * away from the pusher, it would be pushed once.
   */
  public void push() {
    for (Game3Hero sheep : getGame3Manager().getInvolvers()) {
      if (sheep instanceof Sheep && pushable((Sheep) sheep)) {
        ((Sheep) sheep).beingPushed(this);
        // since the sheep is pushable, push once anyway
        sheep.updateRec();
        // Since the location of the sheep is already updated, the sheep that originally 1 distance
        // away is now 2 distance away.
        // if the sheep falls into the sewer, don't push again, or the sheep can't correctly fall.
        if (!sheep.wouldFall()
            && (getX() == sheep.getX() - 2
                || getX() == sheep.getX() + 2
                || getY() == sheep.getY() - 2
                || getY() == sheep.getY() + 2)) {
          ((Sheep) sheep).beingPushed(this);
        }
      }
    }
    pushed = true;
  }

  /**
   * Return whether or not the pusher is going up.
   *
   * @return a boolean indicates whether or not the pusher is going up.
   */
  boolean isGoingUp() {
    return goingUp;
  }

  /**
   * Return whether or not the pusher is going down.
   *
   * @return a boolean indicates whether or not the pusher is going down.
   */
  boolean isGoingDown() {
    return goingDown;
  }

  /**
   * Return whether or not the pusher is going left.
   *
   * @return a boolean indicates whether or not the pusher is going left.
   */
  boolean isGoingLeft() {
    return goingLeft;
  }

  /**
   * Return whether or not the pusher is going right.
   *
   * @return a boolean indicates whether or not the pusher is going right.
   */
  boolean isGoingRight() {
    return goingRight;
  }

  /** The pusher moves up. */
  public void goUp() {
    if (!goingUp) {
      setDirection("up");
    } else {
      super.goUp();
    }
  }

  /** The pusher moves down. */
  public void goDown() {
    if (!goingDown) {
      setDirection("down");
    } else {
      super.goDown();
    }
  }

  /** The pusher moves left. */
  public void goLeft() {
    if (!goingLeft) {
      setDirection("left");
    } else {
      super.goLeft();
    }
  }

  /** The pusher moves right. */
  public void goRight() {
    if (!goingRight) {
      setDirection("right");
    } else {
      super.goRight();
    }
  }

  /** Sheep pusher fall down into the Sewer. */
  @Override
  public void fall() {
    logLives("-");
    System.out.println("Ahhhhhhh");
  }

  /**
   * A helper function that return a rect where the water gun is gonna to be drawn based on the
   * position and direction of the pusher.
   *
   * @return a rect where the water gun is gonna to be drawn.
   */
  private Rect getAniRect() {
    int x = getX();
    int y = getY();
    if (goingRight) {
      return new Rect((x + 1) * scale, y * scale, (x + 3) * scale, (y + 1) * scale);
    } else if (goingLeft) {
      return new Rect((x - 2) * scale, y * scale, x * scale, (y + 1) * scale);
    } else if (goingUp) {
      return new Rect(x * scale, (y - 2) * scale, (x + 1) * scale, y * scale);
    } else if (goingDown) {
      return new Rect(x * scale, (y + 1) * scale, (x + 1) * scale, (y + 3) * scale);
    } else {
      return new Rect(x * scale, (y + 1) * scale, (x + 1) * scale, (y + 3) * scale);
    }
  }

  /**
   * When the player fall into the sewer, reset his position and redraw his bitmap to the down
   * position if the player has more than one life.
   */
  @Override
  protected void newLife() {
    setX(1);
    setY(1);
    setDirection("down");
  }

  /**
   * Draw the sheep pusher and water gun (if pushed) in the map.
   *
   * @param canvas the canvas on which to draw this pusher and water gun.
   */
  @Override
  public void draw(Canvas canvas) {
    drawPusher(canvas);
    drawWaterGun(canvas);
  }

  /**
   * A helper function that draw the sheep pusher based on the direction.
   *
   * @param canvas the canvas on which to draw this pusher.
   */
  private void drawPusher(Canvas canvas) {
    if (goingRight) {
      canvas.drawBitmap(moveRight, null, getRectangle(), null);
    } else if (goingLeft) {
      canvas.drawBitmap(moveLeft, null, getRectangle(), null);
    } else if (goingUp) {
      canvas.drawBitmap(moveUp, null, getRectangle(), null);
    } else if (goingDown) {
      canvas.drawBitmap(moveDown, null, getRectangle(), null);
    } else {
      canvas.drawBitmap(moveDown, null, getRectangle(), null);
    }
  }

  /**
   * A helper function that draw the water gun based on the direction.
   *
   * @param canvas the canvas on which to draw this water gun.
   */
  private void drawWaterGun(Canvas canvas) {
    if (pushed) {
      if (goingRight) {
        canvas.drawBitmap(rightWaterGun, null, getAniRect(), null);
      } else if (goingLeft) {
        canvas.drawBitmap(leftWaterGun, null, getAniRect(), null);
      } else if (goingUp) {
        canvas.drawBitmap(upWaterGun, null, getAniRect(), null);
      } else if (goingDown) {
        canvas.drawBitmap(downWaterGun, null, getAniRect(), null);
      } else {
        canvas.drawBitmap(downWaterGun, null, getAniRect(), null);
      }
      System.out.println("hhhhhhh");
    }
    pushed = false;
  }

  /** Causes this pusher update its status and location. */
  @Override
  public void update() {
    super.update();
    if (getLivesCount() == 0) {
      getGame3Manager().setAlive(false);
    }
  }
}
