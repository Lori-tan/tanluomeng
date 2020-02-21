package com.example.triathlonova.activity.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.triathlonova.R;
import com.example.triathlonova.game3.view.Game3View;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.AddGameInstruction;
import com.example.triathlonova.helper.interfaces.ShowGameScore;

/**
 * A game 3 activity that provides the basic elements of a game and sends corresponding order when
 * the user click the button.
 */
public class Game3Activity extends GameActivity implements AddGameInstruction, ShowGameScore {
  /** A button for moving up. */
  private Button upButton;
  /** A button for moving down. */
  private Button downButton;
  /** A button for moving left. */
  private Button leftButton;
  /** A button for moving right. */
  private Button rightButton;
  /** A button for pushing. */
  private Button pushButton;
  /** A button shows the instruction of the game. */
  private Button instructionButton;
  /** The view for this game. */
  private Game3View gameView;
  /** A text view that shows the score. */
  private TextView game3ScoreText;
  /** A text view that shows the lives remaining. */
  private TextView game3LivesText;
  /** The level of the game. */
  private String level;
  /** A username of this user. */
  private String username;

  /**
   * Create the game 3 activity with game view, buttons, text views and all necessary set ups.
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
    setContentView(R.layout.activity_game3);
    ConstraintLayout constraintLayout = findViewById(R.id.game3Layout);
    // get the level and username from last activity
    Intent intent = getIntent();
    level = intent.getStringExtra("level");
    username = intent.getStringExtra("username");
    getMyDB().updateData(username, DBHelper.getColumnGame3Score(), 0);
    // based on the level the user choose, show the corresponding map
    gameView = new Game3View(this, level, this);
    constraintLayout.addView(gameView);
    // set the default score for game 3 at the beginning of every game
    initializeComponents();
    addButtonListener();
    showScore();
    showLives();
    game3ScoreText.bringToFront();
    game3LivesText.bringToFront();
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onPause() {
    super.onPause();
    updateDB();
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onStop() {
    super.onStop();
    updateDB();
  }

  /** Update the database with the level and game the user is currently playing. */
  private void updateDB() {
    getMyDB().updateData(username, DBHelper.getColumnLevel(), level);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 3);
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /** Initialize all buttons and text views in this activity. */
  public void initializeComponents() {
    upButton = findViewById(R.id.upButton);
    downButton = findViewById(R.id.downButton);
    leftButton = findViewById(R.id.leftButton);
    rightButton = findViewById(R.id.rightButton);
    pushButton = findViewById(R.id.pushButton);
    instructionButton = findViewById(R.id.insButton);
    game3ScoreText = findViewById(R.id.game3Score);
    game3LivesText = findViewById(R.id.game3Lives);
  }

  /** Add button listeners to the activity. */
  public void addButtonListener() {
    moveUpListener(gameView);
    moveDownListener(gameView);
    moveLeftListener(gameView);
    moveRightListener(gameView);
    pushListener(gameView);
    addInstruction();
  }

  /** Construct a listener that allows the pusher to move up when clicking the up button. */
  public void moveUpListener(final Game3View view) {
    upButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.move("up");
            view.invalidate();
          }
        });
  }

  /** Construct a listener that allows the pusher to move down when clicking the down button. */
  public void moveDownListener(final Game3View view) {
    downButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.move("down");
            view.invalidate();
          }
        });
  }

  /** Construct a listener that allows the pusher to move left when clicking the left button. */
  public void moveLeftListener(final Game3View view) {
    leftButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.move("left");
            view.invalidate();
          }
        });
  }

  /** Construct a listener that allows the pusher to move right when clicking the right button. */
  public void moveRightListener(final Game3View view) {
    rightButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.move("right");
            view.invalidate();
          }
        });
  }

  /** Construct a listener that allows the pusher to push when clicking the push button. */
  public void pushListener(final Game3View view) {
    pushButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            view.push();
            view.invalidate();
          }
        });
  }

  /**
   * Construct a listener that shows the user the instruction of this game after clicking the
   * instruction button.
   */
  @Override
  public void addInstruction() {
    instructionButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String instruction =
                "U: move up \n"
                    + "D: move down \n"
                    + "L: move left \n"
                    + "R: move right \n"
                    + "Push: push sheep \n"
                    + "Goal: pushing all the sheep into the sewer. \n"
                    + "The player would lose one life if falling into the sewer.";
            AlertDialog.Builder showDialog = new AlertDialog.Builder(Game3Activity.this);
            showDialog.setTitle("Instruction");
            showDialog.setMessage(instruction);
            showDialog.setPositiveButton("OK", null);
            showDialog.show();
          }
        });
  }

  /** Start the save score activity. */
  public void win() {
    int total =
        Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLocalScore()))
            + gameView.getScore();
    getMyDB().updateData(username, DBHelper.getColumnLocalScore(), total);
    Intent intent = new Intent(".activity.interaction.SaveScoreActivity");
    intent.putExtra("username", username);
    startActivity(intent);
    finish();
  }

  /** Start the choose level activity. */
  public void backToChooseLevel() {
    Intent intent = new Intent(".activity.interaction.ChooseReloadActivity");
    intent.putExtra("username", username);
    startActivity(intent);
    runOnUiThread(
        new Runnable() {
          @Override
          public void run() {
            Toast.makeText(Game3Activity.this, "You lost! Please try again.", Toast.LENGTH_LONG)
                .show();
          }
        });
    finish();
  }

  /** Show the current score in the screen. */
  @Override
  public void showScore() {
    int total =
        Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLocalScore()))
            + gameView.getScore();
    String score = "SCORE: " + total;
    game3ScoreText.setText(score);
  }

  /** Show the remaining lives in the screen. */
  public void showLives() {
    String lives = "LIVES: " + getMyDB().queryData(username, DBHelper.getColumnLives());
    game3LivesText.setText(lives);
  }

  /**
   * Return the number of lives remaining.
   *
   * @return the number of lives remaining.
   */
  public int getLivesCount() {
    return Integer.valueOf(getMyDB().queryData(username, DBHelper.getColumnLives()));
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
