package com.example.triathlonova.game3.model.hero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.triathlonova.R;
import com.example.triathlonova.game3.presenter.processor.Game3Manager;

/** A sheep that can be pushed by the sheep pusher. */
public class Sheep extends Game3Hero {

  /**
   * A bitmap for the sheep, normal sheep for level 1, tall sheep for level 2, and fluffy sheep for
   * level 3.
   */
  private Bitmap sheep;
  /** the level of the game. */
  private String level;

  /**
   * Constructs a new sheep.
   *
   * @param rec the rectangle that represent this sheep.
   * @param manager the push sheep manager that this sheep is in.
   * @param context the environment.
   * @param level the level of the game.
   */
  public Sheep(Rect rec, Game3Manager manager, Context context, String level) {
    super(rec, manager);
    this.level = level;
    initializeBitmaps(context);
  }

  /**
   * Initialize the bitmaps that would be used when drawing based on the level the user chooses..
   *
   * @param context the environment
   */
  private void initializeBitmaps(Context context) {
    switch (level) {
      case "1":
        sheep = BitmapFactory.decodeResource(context.getResources(), R.drawable.sheep);
        break;
      case "2":
        sheep = BitmapFactory.decodeResource(context.getResources(), R.drawable.tall_sheep);
        break;
      case "3":
        sheep = BitmapFactory.decodeResource(context.getResources(), R.drawable.fluffy_sheep);
        break;
    }
  }

  /** Override the newLife method in super class. */
  @Override
  protected void newLife() {}

  /**
   * Draw the Sheep in the map.
   *
   * @param canvas the canvas on which to draw this sheep.
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawBitmap(sheep, null, getRectangle(), null);
  }

  /**
   * the sheep is pushed to a new coordinate, to the up, down, left or right, depends on how the
   * pusher pushes.
   *
   * @param pusher the pusher who pushes this sheep.
   */
  void beingPushed(SheepPusher pusher) {
    if (pusher.isGoingUp()) {
      goUp();
    } else if (pusher.isGoingDown()) {
      goDown();
    } else if (pusher.isGoingLeft()) {
      goLeft();
    } else if (pusher.isGoingRight()) {
      goRight();
    }
  }

  /** Sheep falls down into the Sewer. */
  @Override
  public void fall() {
    getGame3Manager().removeSheep(this);
    getGame3Manager().setGetScore(true);
    getGame3Manager().addCount();
    System.out.println("Mieeeee");
  }
}
