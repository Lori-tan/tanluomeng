package com.example.triathlonova.helper.interfaces;

/**
 * An interface of sending out updates to a group of object.
 *
 * <p>Because Java doesn't support multiple inheritance, as a class, Observable sometimes cannot be
 * used. And Java supports multiple interface, so we design this interface for Observable-Observer
 * design patter. ObserverInterface is use together with this interface.
 */
public interface ObservableInterface {

  /**
   * Add an OberverInterface
   *
   * @param o The ObserverInterface desired to add.
   */
  void addObserver(ObserverInterface o);

  /** Notify the Observer certain information. */
  void notifyObservers();

  /** Notify the Observer the given argument. */
  void notifyObservers(Object arg);

  /** Set change to the Observer. */
  void setChanged();

  /** Reset Observer. Clean the history. */
  void clearChanged();

  /** Check if a change has been made. */
  boolean hasChanged();
}
