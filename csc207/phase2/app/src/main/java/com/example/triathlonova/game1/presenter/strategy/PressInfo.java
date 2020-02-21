package com.example.triathlonova.game1.presenter.strategy;

/**
 * A class that keeps track of information of two variables whose values are initialized from the
 * listener methods to the buttons in Game1Activity.
 */
public class PressInfo {

  /** A boolean variable that describes whether the left button or the right button was pressed. */
  private boolean toRight;
  /** An int variable that describes how long the button was pressed. */
  private int pressPower;

  /**
   * Initialize a new PressInfo with the given boolean value of toRight parameter and int value of
   * pressPower parameter.
   *
   * @param toRight the boolean value that variable toRight is set to.
   * @param pressPower the int value that variable pressPower is set to.
   */
  public PressInfo(boolean toRight, int pressPower) {
    this.toRight = toRight;
    this.pressPower = pressPower;
  }

  /**
   * Return the boolean variable toRight.
   *
   * @return the value of the variable toRight.
   */
  public boolean isToRight() {
    return toRight;
  }

  /**
   * Return the int variable pressPower.
   *
   * @return the value of the variable pressPower.
   */
  public int getPressPower() {
    return pressPower;
  }
}
