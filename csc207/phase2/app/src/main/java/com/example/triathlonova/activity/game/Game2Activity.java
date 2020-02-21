package com.example.triathlonova.activity.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;

import com.example.triathlonova.R;
import com.example.triathlonova.helper.database.DBHelper;
import com.example.triathlonova.helper.interfaces.AddGameInstruction;

/**
 * the game 2 activity provides the starting page of the game 2 and instruction button to show how
 * to play the game, also the play button to activate the main game 2 activity.
 */
public class Game2Activity extends GameActivity implements AddGameInstruction {

  /** The level of the game. */
  private String level;
  /** A username of this user. */
  private String username;
  /** The instruction button of the game. */
  private Button game2Instruction;

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
    setContentView(R.layout.activity_game2);
    addListenerGame2InstructionButton();
    Intent intent = getIntent();
    level = intent.getStringExtra("level");
    username = intent.getStringExtra("username");

    addListenerStartButton();
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onPause() {
    super.onPause();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), level);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 2);
  }

  /**
   * Save the level and game the user is currently playing to the database when the user leaves the
   * game.
   */
  @Override
  protected void onStop() {
    super.onStop();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), level);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 2);
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /** start the main game 2 activity with the correspond user and level. */
  public void startGame() {
    Intent intent = new Intent(".activity.game.Game2MainActivity");
    intent.putExtra("level", level);
    intent.putExtra("username", username);
    startActivity(intent);
    finish();
  }

  /** Add a listener for the button starting the Game2MainActivity. */
  private void addListenerStartButton() {
    ImageButton startButton = findViewById(R.id.startButton);
    startButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startGame();
          }
        });
  }

  /** Add a listener for the button Game2Instruction. */
  private void addListenerGame2InstructionButton() {
    game2Instruction = findViewById(R.id.game2Instruction);
    game2Instruction.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            addInstruction();
          }
        });
  }

  /**
   * Construct a listener that shows the user the instruction of this game after clicking the
   * instruction button.
   */
  @Override
  public void addInstruction() {
    game2Instruction.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String instruction =
                "The hot air balloon automatically rises\n"
                    + "Touch Screen: the hot air balloon drops\n"
                    + "Goal: Collect as many gems as possible without colliding with lightnings,"
                    + " buildings, and clouds. Colliding with those will result in lives loss.\n"
                    + "You will lose the game if you hit too many obstacles and lose all lives.\n"
                    + "The very last gem is the winning condition to play the next game. \n"
                    + "Have FUN!";
            AlertDialog.Builder showDialog = new AlertDialog.Builder(Game2Activity.this);
            showDialog.setTitle("Instruction");
            showDialog.setMessage(instruction);
            showDialog.setPositiveButton("OK", null);
            showDialog.show();
          }
        });
  }
}
