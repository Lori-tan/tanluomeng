package com.example.triathlonova.activity.interaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triathlonova.R;
import com.example.triathlonova.helper.database.DBHelper;

/** An activity for the interface of new user signing up. */
public class SignUpActivity extends AppCompatActivity {

  /** The database helper. */
  private DBHelper myDB;
  /** A view box with text. */
  private EditText username;
  /** A view box with text. */
  private EditText password;

  /**
   * Create this activity with views, button, and database initialized.
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
    setContentView(R.layout.activity_sign_up);
    myDB = new DBHelper(this);
    addListenerOnButton();
  }

  /** Close the database when destroy this activity. */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    myDB.close();
  }

  /** Store the user-input username and password into the DB if those are valid. */
  private void addListenerOnButton() {
    username = findViewById(R.id.usernameText);
    password = findViewById(R.id.passwordText);
    Button signUpButton = findViewById(R.id.signUpButton);
    signUpButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (username == null || username.getText().toString().equals("")) {
              Toast.makeText(SignUpActivity.this, "Please enter username.", Toast.LENGTH_LONG)
                  .show();
              return;
            }
            if (myDB.queryData(username.getText().toString()).getCount() > 0) {
              Toast.makeText(SignUpActivity.this, "Has existed!", Toast.LENGTH_LONG).show();
            } else {
              myDB.insertUser(username.getText().toString(), password.getText().toString());
              Toast.makeText(SignUpActivity.this, "Sign up successfully!", Toast.LENGTH_LONG)
                  .show();
              SignUpActivity.this.finish();
            }
          }
        });
  }
}
