package com.example.triathlonova.activity.interaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.triathlonova.R;
import com.example.triathlonova.activity.game.GameActivity;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the interface of saving score after complete the game. */
public class SaveScoreActivity extends GameActivity {

  /** This account's username. */
  private String username;
  /** The database helper. */
  private DBHelper myDB;
  /** A view box with text. */
  private TextView historyScore;
  /** A view box with text. */
  private TextView newScore;
  /** A button for UI. */
  private Button yesButton;
  /** A button for UI */
  private Button noButton;

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
    setContentView(R.layout.activity_save_score);
    username = getIntent().getStringExtra("username");
    myDB = getMyDB();
    initializeComponents();
    addButtonListener();
    showScores();
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onPause() {
    super.onPause();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), 0);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 0);
  }

  /** Stop this activity with no updates tho the database. */
  @Override
  protected void onStop() {
    super.onStop();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), 0);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 0);
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /** Find the corresponding views in UML and initialize all variable. */
  public void initializeComponents() {
    yesButton = findViewById(R.id.yesButton);
    noButton = findViewById(R.id.noButton);
    historyScore = findViewById(R.id.historyScore);
    newScore = findViewById(R.id.newScore);
  }

  /** Add listener for all buttons for UI. */
  public void addButtonListener() {
    addListenerYesButton();
    addListenerNoButton();
  }

  /** Save this local score when the Yes button is pressed. */
  private void addListenerYesButton() {
    yesButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.interaction.ScoreBoardActivity");
            intent.putExtra("username", username);
            int score = Integer.valueOf(myDB.queryData(username, DBHelper.getColumnLocalScore()));
            myDB.updateData(username, "SCORE", score);
            startActivity(intent);
            finish();
          }
        });
  }

  /** Don't save this local score when the No button is pressed. */
  private void addListenerNoButton() {
    noButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.interaction.ScoreBoardActivity");
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
          }
        });
  }

  /** Set the history score to this local score and show it to the user. */
  private void showScores() {
    historyScore.setText(myDB.queryData(username, DBHelper.getColumnScore()));
    newScore.setText(myDB.queryData(username, DBHelper.getColumnLocalScore()));
  }
}
