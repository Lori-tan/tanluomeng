package com.example.triathlonova.activity.interaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.triathlonova.R;
import com.example.triathlonova.activity.game.GameActivity;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the interface of choosing to reload existing game or new game. */
public class ChooseReloadActivity extends GameActivity {
  /** A button to load an existing game. */
  private Button loadGame;
  /** A button to start a new game. */
  private Button newGame;
  /** A name of this user. */
  private String username;

  /**
   * Create this activity with view, buttons, and database for this user initialized.
   *
   * @param savedInstanceState saved data from the system
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }
    setContentView(R.layout.activity_choose_reload);
    username = getIntent().getStringExtra("username");
    initializeComponents();
    addListeners();
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /** Initialize the two buttons: one to load existing game, one to start a new game. */
  public void initializeComponents() {
    loadGame = findViewById(R.id.loadGame);
    newGame = findViewById(R.id.newGame);
  }

  /** Add listener for each of the two button. */
  private void addListeners() {
    addLoadGameListener();
    addNewGameListener();
  }

  /** Add listener for the button to load existing game. */
  private void addLoadGameListener() {
    loadGame.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String game = getMyDB().queryData(username, DBHelper.getColumnGame());
            String level = getMyDB().queryData(username, DBHelper.getColumnLevel());
            startLoadedGame(game, level);
          }
        });
  }

  /**
   * Return to a existing game currently at the game and level determined by the passed in
   * parameter.
   *
   * @param columnGame the game the user last quits at.
   * @param columnLevel the level the user last selects to play at.
   */
  private void startLoadedGame(String columnGame, String columnLevel) {
    Intent intent;
    switch (columnGame) {
      default:
        intent = new Intent(".activity.interaction.ChooseLevelActivity");
        break;
      case "1":
        intent = new Intent(".activity.game.Game1Activity");
        break;
      case "2":
        intent = new Intent(".activity.game.Game2Activity");
        break;
      case "3":
        intent = new Intent(".activity.game.Game3Activity");
        break;
    }
    if (!columnLevel.equals("0")) {
      intent.putExtra("level", columnLevel);
    }
    intent.putExtra("username", username);
    startActivity(intent);
    finish();
  }

  /** Add listener for the button to start a new game. */
  private void addNewGameListener() {
    newGame.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            getMyDB().resetData(username);
            Intent intent = new Intent(".activity.interaction.ChooseLevelActivity");
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
          }
        });
  }
}
