package com.example.triathlonova.activity.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.triathlonova.R;
import com.example.triathlonova.game1.presenter.strategy.PressInfo;
import com.example.triathlonova.game1.view.Game1View;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.AddGameInstruction;
import com.example.triathlonova.helper.interfaces.ShowGameScore;

/**
 * A game 1 activity that provides the basic elements of a game and sends corresponding order when
 * the user clicks the button.
 */
public class Game1Activity extends GameActivity implements AddGameInstruction, ShowGameScore {

  /** A button for jump to the left. */
  private Button jumpLeftButton;
  /** A button for jump to the right. */
  private Button jumpRightButton;
  /** A button to display instruction of this game. */
  private Button gameInstruction;
  /** A text view that shows the score. */
  private TextView game1ScoreText;
  /** A text view that shows the lives remaining. */
  private TextView game1LivesText;
  /** A username of this user. */
  private String username;
  /** A time tracker variable. */
  private long previousTime;
  /** A time tracker variable. */
  private long nextTime;
  /** The level of the game. */
  private String level;
  /** The view that works in association with this activity. */
  private Game1View gameView;

  /**
   * Create the game 1 activity with game view, buttons, text views and all necessary set ups.
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
    setContentView(R.layout.activity_game1);

    Intent intent = getIntent();
    username = intent.getStringExtra("username");
    level = intent.getStringExtra("level");
    // must update database before creating the new game view
    getMyDB().updateData(username, DBHelper.getColumnGame1Score(), 100);
    ConstraintLayout constraintLayout = findViewById(R.id.game1Layout);
    gameView = new Game1View(this, this, level);
    constraintLayout.addView(gameView);

    initializeComponents();
    addButtonListener();
    showScore();
    showLives();
    game1ScoreText.bringToFront();
    game1LivesText.bringToFront();
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onPause() {
    super.onPause();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), level);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 1);
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onStop() {
    super.onStop();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), level);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 1);
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /**
   * Initialize the four buttons: jumpLeftButton, jumpRightButton, jumpToGame2Button, and
   * gameInstruction and two text views: game1ScoresText, and game1LivesText.
   */
  public void initializeComponents() {
    jumpLeftButton = findViewById(R.id.game1JumpLeft);
    jumpRightButton = findViewById(R.id.game1JumpRight);
    gameInstruction = findViewById(R.id.game1Instruction);
    game1ScoreText = findViewById(R.id.game1Score);
    game1LivesText = findViewById(R.id.game1Lives);
  }

  /** Add button listeners to the activity. */
  public void addButtonListener() {
    addJumpLeftButtonListener();
    addJumpRightButtonListener();
    addInstruction();
  }

  /** Add a listener for the jump left button. */
  private void addJumpLeftButtonListener() {
    jumpLeftButton.setOnTouchListener(
        new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
              previousTime = System.currentTimeMillis();
            }
            if (action == MotionEvent.ACTION_UP) {
              nextTime = System.currentTimeMillis();
              setChanged();
              notifyObservers(new PressInfo(false, (int) ((nextTime - previousTime) / 10)));
            }

            return false;
          }
        });
  }

  /** Add a listener for the jump right button. */
  private void addJumpRightButtonListener() {
    jumpRightButton.setOnTouchListener(
        new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
              previousTime = System.currentTimeMillis();
            }
            if (action == MotionEvent.ACTION_UP) {
              nextTime = System.currentTimeMillis();
              setChanged();
              notifyObservers(new PressInfo(true, (int) ((nextTime - previousTime) / 10)));
            }

            return false;
          }
        });
  }

  /** Start the activity for the next game. */
  public void startGame2() {
    getMyDB().updateData(username, DBHelper.getColumnLocalScore(), gameView.getScore());
    Intent intent = new Intent(".activity.game.Game2Activity");
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
            Toast.makeText(Game1Activity.this, "You lost! Please try again.", Toast.LENGTH_LONG)
                .show();
          }
        });
    finish();
  }

  /** Display current score through setting it onto the score view box. */
  @Override
  public void showScore() {
    String score = "SCORE: " + gameView.getScore();
    game1ScoreText.setText(score);
  }

  /** Display remaining lives through setting it onto the lives view box. */
  public void showLives() {
    String lives = "LIVES: " + getMyDB().queryData(username, DBHelper.getColumnLives());
    game1LivesText.setText(lives);
  }

  /**
   * Construct a listener that shows the user the instruction of this game after clicking the
   * instruction button.
   */
  @Override
  public void addInstruction() {
    gameInstruction.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String instruction =
                "JUMP LEFT: the hero jump to its left \n"
                    + "JUMP RIGHT: hero jump to its right \n"
                    + "Goal: Collect the gem at the top. \n"
                    + "The player could earn one more life if collected a hearts."
                    + "The player would lose one life if fell down the cliff";
            AlertDialog.Builder showDialog = new AlertDialog.Builder(Game1Activity.this);
            showDialog.setTitle("Instruction");
            showDialog.setMessage(instruction);
            showDialog.setPositiveButton("OK", null);
            showDialog.show();
          }
        });
  }

  /** Update the number of lives remaining to the database. */
  public void storeLives(int lives) {
    getMyDB().updateData(username, DBHelper.getColumnLives(), lives);
  }

  /**
   * Return the username of the user.
   *
   * @return the username of the user.
   */
  public String getUsername() {
    return username;
  }
}
