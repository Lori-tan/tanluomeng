package com.example.triathlonova.game3.presenter.builder;

import com.example.triathlonova.game3.presenter.processor.Game3Manager;

/**
 * A builder factory that creates and returns a builder of a level for Game3Manager based on the
 * level of the game.
 */
public class BuilderFactory {

  /**
   * Return an initialized Game3Builder object specific to the given level in the parameter.
   *
   * @param level the level of the game.
   * @param manager the push sheep manager where the builder is used.
   */
  public Game3Builder makeBuilder(String level, Game3Manager manager) {
    switch (level) {
      case "1":
        return new Level1Builder(manager);
      case "2":
        return new Level2Builder(manager);
      case "3":
        return new Level3Builder(manager);
      default:
        return null;
    }
  }
}
