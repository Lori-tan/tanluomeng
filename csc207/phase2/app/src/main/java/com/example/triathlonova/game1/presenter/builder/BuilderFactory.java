package com.example.triathlonova.game1.presenter.builder;

/**
 * A factory to create specific Game1Builder object responsible for a certain level of this game.
 */
public class BuilderFactory {

  /**
   * Return an initialized Game1Builder object specific to the given level in parameter.
   *
   * @param level a string of either 1 or 2 or 3.
   * @return a builder of the wanted level.
   */
  public Game1Builder constructBuilderOfLevel(String level) {
    switch (level) {
      case "1":
        return new Level1Builder();
      case "2":
        return new Level2Builder();
      case "3":
        return new Level3Builder();
      default:
        return null;
    }
  }
}
