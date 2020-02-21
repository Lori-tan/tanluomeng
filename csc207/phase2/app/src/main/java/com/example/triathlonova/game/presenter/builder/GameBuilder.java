package com.example.triathlonova.game.presenter.builder;

/** A builder class has the responsibility of initializing all in-game objects. */
public abstract class GameBuilder {

  /** The scaled size of the rectangle around the hero. */
  private int characterScale;

  /**
   * Return a int variable of scaled size of the hero.
   *
   * @return the variable characterScale.
   */
  protected int getCharacterScale() {
    return characterScale;
  }

  /**
   * Set the variable to the value of the parameter.
   *
   * @param characterScale the value set the variable to
   */
  protected void setCharacterScale(int characterScale) {
    this.characterScale = characterScale;
  }
}
