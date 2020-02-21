package com.example.triathlonova.activity.interaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.triathlonova.R;
import com.example.triathlonova.activity.game.GameActivity;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the interface of choosing level of the game. */
public class ChooseLevelActivity extends GameActivity {

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
    setContentView(R.layout.activity_choose_level);
    username = getIntent().getStringExtra("username");
    addListenerLevel1Button();
    addListenerLevel2Button();
    addListenerLevel3Button();
    getMyDB().updateData(username, DBHelper.getColumnLocalScore(), 0);
  }

  /** Pause this activity with no updates to database. */
  @Override
  protected void onPause() {
    super.onPause();
    getMyDB().updateData(username, DBHelper.getColumnLevel(), 0);
    getMyDB().updateData(username, DBHelper.getColumnGame(), 0);
  }

  /** Stop this activity with no updates to database. */
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

  /** Add a listener for the level1 button. */
  private void addListenerLevel1Button() {
    Button level1Button = findViewById(R.id.Level1);
    level1Button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.game.Game1Activity");
            intent.putExtra("level", "1");
            intent.putExtra("username", username);
            startActivity(intent);
            Toast.makeText(
                    ChooseLevelActivity.this,
                    "You are in level 1 of " + "Triathlonova!",
                    Toast.LENGTH_SHORT)
                .show();
            finish();
          }
        });
  }

  /** Add a listener for the level2 button. */
  private void addListenerLevel2Button() {
    Button level2Button = findViewById(R.id.Level2);
    level2Button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.game.Game1Activity");
            intent.putExtra("level", "2");
            intent.putExtra("username", username);
            startActivity(intent);
            Toast.makeText(
                    ChooseLevelActivity.this,
                    "You are in level 2 of " + "Triathlonova!",
                    Toast.LENGTH_SHORT)
                .show();
            finish();
          }
        });
  }

  /** Add a listener for the level3 button. */
  private void addListenerLevel3Button() {
    Button level3Button = findViewById(R.id.Level3);
    level3Button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.game.Game1Activity");
            intent.putExtra("level", "3");
            intent.putExtra("username", username);
            startActivity(intent);
            Toast.makeText(
                    ChooseLevelActivity.this,
                    "You are in level 3 of " + "Triathlonova!",
                    Toast.LENGTH_SHORT)
                .show();
            finish();
          }
        });
  }
}
