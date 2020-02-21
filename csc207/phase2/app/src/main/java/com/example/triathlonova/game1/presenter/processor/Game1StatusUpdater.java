package com.example.triathlonova.game1.presenter.processor;

import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

import java.util.Vector;

/** A game status tracker and updater class. */
class Game1StatusUpdater implements ObservableInterface {

  /** Whether there is a new message to be sent out. */
  private boolean changed;
  /** A collection of observers of this class. */
  private Vector<ObserverInterface> obs;

  /** Initialize a Game1StatusUpdater Object. */
  Game1StatusUpdater() {
    changed = false;
    obs = new Vector<>();
  }

  /** Send a change in game status. */
  void playerDied() {
    setChanged();
    notifyObservers("dead");
  }

  /** Send a change in game status. */
  void playerWon() {
    setChanged();
    notifyObservers("won");
  }

  @Override
  public void addObserver(ObserverInterface o) {
    if (o == null) throw new NullPointerException();
    if (!obs.contains(o)) {
      obs.addElement(o);
    }
  }

  @Override
  public void notifyObservers() {
    notifyObservers(null);
  }

  @Override
  public void notifyObservers(Object arg) {
    Object[] arrLocal;

    synchronized (this) {
      if (!hasChanged()) return;
      arrLocal = obs.toArray();
      clearChanged();
    }

    for (int i = arrLocal.length - 1; i >= 0; i--)
      ((ObserverInterface) arrLocal[i]).update(this, arg);
  }

  @Override
  public void setChanged() {
    changed = true;
  }

  @Override
  public void clearChanged() {
    changed = false;
  }

  @Override
  public boolean hasChanged() {
    return changed;
  }
}
