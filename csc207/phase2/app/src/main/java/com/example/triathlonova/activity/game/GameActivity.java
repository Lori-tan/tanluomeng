package com.example.triathlonova.activity.game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.ObservableInterface;
import com.example.triathlonova.helper.interfaces.ObserverInterface;

import java.util.Vector;

public abstract class GameActivity extends AppCompatActivity implements ObservableInterface {

  /** A database helper. */
  private DBHelper myDB;
  /** A collection of ObserverInterface. */
  private Vector<ObserverInterface> obs = new Vector<>();
  /** Indicating whether there is a new change. */
  private boolean changed = false;

  /**
   * Create a game activity with initialized variables and all necessary set ups.
   *
   * @param savedInstanceState saved data from the system
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    myDB = new DBHelper(this);
    obs = new Vector<>();
  }

  /**
   * Return a database helper initialized by this activity.
   *
   * @return a database helper
   */
  public DBHelper getMyDB() {
    return myDB;
  }

  /**
   * Add an observer of this activity to the collection of observers.
   *
   * @param o an object implementing ObserverInterface.
   */
  @Override
  public void addObserver(ObserverInterface o) {
    if (o == null) throw new NullPointerException();
    if (!obs.contains(o)) {
      obs.addElement(o);
    }
  }

  /** Inform all observers in the collection that there's a change. */
  @Override
  public void notifyObservers() {
    notifyObservers(null);
  }

  /**
   * Inform all observers in the collection that there's a change and provide all observers with a
   * message.
   *
   * @param arg a message with any type.
   */
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

  /** Store that there's a new change. */
  @Override
  public void setChanged() {
    changed = true;
  }

  /** Store that a change has been cleared. */
  @Override
  public void clearChanged() {
    changed = false;
  }

  /**
   * Return a boolean indicating whether there's a new change.
   *
   * @return variable changed value.
   */
  @Override
  public boolean hasChanged() {
    return changed;
  }
}
