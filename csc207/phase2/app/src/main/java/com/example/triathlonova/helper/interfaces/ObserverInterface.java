package com.example.triathlonova.helper.interfaces;

/**
 * An interface of updating after receive a message. This interface is used together with
 * ObservableInterface.
 */
public interface ObserverInterface {

  /**
   * This method is called whenever the observed object is changed. An application calls an
   * Observable object's notifyObservers method to have all the object's observers notified of the
   * change.
   *
   * @param o the observable object.
   * @param arg an argument passed to the notifyObservers method.
   */
  void update(ObservableInterface o, Object arg);
}
