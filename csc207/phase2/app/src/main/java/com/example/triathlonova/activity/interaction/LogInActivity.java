package com.example.triathlonova.activity.interaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.triathlonova.R;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the log in interface. */
public class LogInActivity extends AppCompatActivity {

  /** A database helper. */
  DBHelper myDB;
  /** This account's name. */
  private EditText username;
  /** The account's password. */
  private EditText password;

  /**
   * Create this activity with view, buttons, and database initialized.
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
    setContentView(R.layout.activity_log_in);
    myDB = new DBHelper(this);
    addListenerOnLogInButton();
    addListenerOnSignUpButton();
    addListenerOnShowButton();
  }

  /** Stop this activity and close the database. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    myDB.close();
  }

  /** Add a listener for the button to log in. */
  private void addListenerOnLogInButton() {
    username = findViewById(R.id.usernameText);
    password = findViewById(R.id.passwordText);
    Button logInButton = findViewById(R.id.logInButton);
    logInButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (username == null || username.getText().toString().equals("")) {
              Toast.makeText(LogInActivity.this, "Please enter your username.", Toast.LENGTH_LONG)
                  .show();
              return;
            }
            String result =
                myDB.queryData(username.getText().toString(), DBHelper.getColumnPassword());
            if (result == null) {
              Toast.makeText(LogInActivity.this, "No username! Please sign up.", Toast.LENGTH_LONG)
                  .show();
            } else {
              if (password.getText().toString().equals(result)) {
                Toast.makeText(
                        LogInActivity.this,
                        "Log in successfully!"
                            + " Now at level "
                            + myDB.queryData(
                                username.getText().toString(), DBHelper.getColumnLevel()),
                        Toast.LENGTH_LONG)
                    .show();
                Intent intent = new Intent(".activity.interaction.ChooseReloadActivity");
                intent.putExtra("username", username.getText().toString());
                startActivity(intent);
              } else {
                Toast.makeText(LogInActivity.this, "Incorrect password.", Toast.LENGTH_LONG).show();
              }
            }
          }
        });
  }

  /** Add a listener for the button to sign up. */
  private void addListenerOnSignUpButton() {
    Button signUpButton = findViewById(R.id.signUpButton);
    signUpButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(".activity.interaction.SignUpActivity");
            startActivity(intent);
          }
        });
  }

  /** Add a listener for the button to show current existing accounts on this host. */
  private void addListenerOnShowButton() {
    Button showButton = findViewById(R.id.showButton);
    showButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            StringBuilder buffer = new StringBuilder();
            try (Cursor result = myDB.getAllData()) {

              if (result.getCount() == 0) {
                Toast.makeText(LogInActivity.this, "No player now.", Toast.LENGTH_LONG).show();
              } else {
                while (result.moveToNext()) {
                  String str =
                      result.getString(result.getColumnIndex(DBHelper.getColumnUsername()))
                          + " at level "
                          + result.getString(result.getColumnIndex(DBHelper.getColumnLevel()))
                          + " at game "
                          + result.getString(result.getColumnIndex(DBHelper.getColumnGame()))
                          + "\n";
                  buffer.append(str);
                }
              }
            }
            AlertDialog.Builder showDialog = new AlertDialog.Builder(LogInActivity.this);
            showDialog.setTitle("ScoreBoard");
            showDialog.setMessage(buffer.toString());
            showDialog.setPositiveButton("OK", null);
            showDialog.show();
          }
        });
  }
}
