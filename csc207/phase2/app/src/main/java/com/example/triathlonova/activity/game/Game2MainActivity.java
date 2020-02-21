package com.example.triathlonova.activity.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.triathlonova.R;
import com.example.triathlonova.game2.view.Game2View;
import com.example.triathlonova.helper.database.DBHelper;

/**
 * the main activity for game 2 that provides the basic elements of a game and sends corresponding
 * order when the user clicks the screen.
 */
public class Game2MainActivity extends GameActivity {
  /** The level of the game. */
  private String level;
  /** A username of this user. */
  private String username;
  /** A button for player to fly down. */
  private Button flyDownButton;
  /** The view that works in association with this activity. */
  private Game2View gameView;
  /** A text view that shows the score. */
  private TextView game2ScoreText;
  /** A text view that shows the lives remaining. */
  private TextView game2LivesText;

  /**
   * Create the game 2 activity with game view, buttons, text views and all necessary set ups.
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
    setContentView(R.layout.activity_game2_main);

    Intent intent = getIntent();
    level = intent.getStringExtra("level");
    username = intent.getStringExtra("username");
    getMyDB().updateData(username, DBHelper.getColumnGame2Score(), 0);
    ConstraintLayout constraintLayout = findViewById(R.id.game2_main_layout);
    gameView = new Game2View(this, this, level);
    constraintLayout.addView(gameView);

    initializeComponents();
    addButtonListener();
    showScore();
    showLives();
    game2ScoreText.bringToFront();
    game2LivesText.bringToFront();
  }

  /** Add button listeners to the activity. */
  public void addButtonListener() {
    addDownButtonListener(gameView);
  }

  /** Add a listener for the button to sink the balloon. */
  private void addDownButtonListener(final Game2View view) {
    flyDownButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.updateHero();
          }
        });
  }

  /** Start the activity for the next game. */
  public void startGame3() {
    int total =
        Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLocalScore()))
            + gameView.getScore();
    getMyDB().updateData(username, DBHelper.getColumnLocalScore(), total);
    Intent intent = new Intent(".activity.game.Game3Activity");
    intent.putExtra("level", level);
    intent.putExtra("username", username);
    startActivity(intent);
    finish();
  }

  /** Return to the activity of the choose level interface. */
  public void backToChooseLevel() {
    Intent intent = new Intent(".activity.interaction.ChooseLevelActivity");
    intent.putExtra("username", username);
    startActivity(intent);
    runOnUiThread(
        new Runnable() {
          @Override
          public void run() {
            Toast.makeText(Game2MainActivity.this, "You lost! Please try again.", Toast.LENGTH_LONG)
                .show();
          }
        });
    finish();
  }

  /**
   * Initialize the 2 buttons: flyDownButton and JumpToGame3Button. and two text views:
   * game2ScoresText, and game2LivesText.
   */
  public void initializeComponents() {
    flyDownButton = findViewById(R.id.game2down);
    game2ScoreText = findViewById(R.id.game2Score);
    game2LivesText = findViewById(R.id.game2Lives);
  }

  /** Display current score through setting it onto the score view box. */
  public void showScore() {
    int total =
        Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLocalScore()))
            + gameView.getScore();
    String score = "SCORE: " + total;
    game2ScoreText.setText(score);
  }

  /** Display remaining lives through setting it onto the lives view box. */
  public void showLives() {
    String lives = "LIVES: " + getMyDB().queryData(username, DBHelper.getColumnLives());
    game2LivesText.setText(lives);
  }

  /** Update the number of lives remaining to the database. */
  public void storeLives(int livesCount) {
    getMyDB().updateData(username, DBHelper.getColumnLives(), livesCount);
  }

  /** @return the number of lives remaining. */
  public int getLivesCount() {
    return Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLives()));
  }

  /** @return the username of the user. */
  public String getUsername() {
    return username;
  }
}
