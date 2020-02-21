package com.example.triathlonova.activity.interaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.triathlonova.R;
import com.example.triathlonova.activity.game.GameActivity;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the interface of displaying scores for multiple uers. */
public class ScoreBoardActivity extends GameActivity {

  /** A view box with text. */
  private TextView names;
  /** A view box with text. */
  private TextView scores;
  /** A button for UI. */
  private Button restart;
  /** A button for UI. */
  private Button login;
  /** The database helper. */
  private DBHelper myDB;
  /** This account's username. */
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
    setContentView(R.layout.activity_score_board);
    myDB = new DBHelper(this);
    username = this.getIntent().getStringExtra("username");

    initializeComponents();
    addButtonListener();
    showTop10();
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    getMyDB().close();
  }

  /** Find the corresponding views in UML and initialize all variables. */
  public void initializeComponents() {
    names = findViewById(R.id.username);
    scores = findViewById(R.id.score);
    restart = findViewById(R.id.restartButton);
    login = findViewById(R.id.backToLoginButton);
  }

  /** Add listener for all buttons for UI. */
  public void addButtonListener() {
    addRestartListener();
    addLoginListener();
  }

  /** Take the user back to the choose level interface to play again. */
  public void addRestartListener() {
    restart.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.interaction.ChooseLevelActivity");
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
          }
        });
  }

  /** Take the user back to the initial log in interface when the app is just opened. */
  public void addLoginListener() {
    login.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(ScoreBoardActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
          }
        });
  }

  /** Show the top 10 score of this game stored on this current host. */
  public void showTop10() {
    Cursor result = null;
    SQLiteDatabase DB = null;
    StringBuilder user = new StringBuilder();
    user.append("USERNAME \n");
    StringBuilder score = new StringBuilder();
    score.append("SCORE \n");
    String str3 = "";
    String str4 = "";
    try {
      DB = myDB.getReadableDatabase();
      result =
          DB.query(
              DBHelper.getTableName(),
              null,
              null,
              null,
              null,
              null,
              DBHelper.getColumnScore() + " DESC");
      if (result.getCount() == 0) {
        user.append("None");
        score.append("None");
      } else {
        int i = 1;
        boolean found = false;
        while (result.moveToNext()) {
          if (i < 11) {
            String str1 =
                i
                    + ". "
                    + result.getString(result.getColumnIndex(DBHelper.getColumnUsername()))
                    + "\n";
            user.append(str1);
            String str2 = result.getString(result.getColumnIndex(DBHelper.getColumnScore())) + "\n";
            score.append(str2);
          }
          if (result
              .getString(result.getColumnIndex(DBHelper.getColumnUsername()))
              .equals(username)) {
            str3 = "\n\n\n" + "Your Position:\n" + i + ". " + username;
            str4 = "\n\n\n\n" + result.getString(result.getColumnIndex(DBHelper.getColumnScore()));
            found = true;
          }
          if (i > 10 && found) {
            break;
          }
          i += 1;
        }
      }
    } finally {
      if (result != null) {
        result.close();
        DB.close();
      }
    }
    user.append(str3);
    score.append(str4);
    names.setText(user.toString());
    scores.setText(score.toString());
  }
}
