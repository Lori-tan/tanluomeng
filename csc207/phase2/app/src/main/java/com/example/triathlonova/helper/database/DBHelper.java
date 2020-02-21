package com.example.triathlonova.helper.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBHelper.java: A database helper applying APIs for storing and fetching data.
 *
 * <p>Maintained by Yuqin Yan, if you have any suggestions or find errors on this DBHelper, please
 * contact: yuqin.yan@mail.utoronto.ca
 */
public class DBHelper extends SQLiteOpenHelper {

  /** Information(column names, table name, etc) of this database. */
  private static final String COLUMN_SCORE = "SCORE";

  private static final String COLUMN_LOCAL_SCORE = "LOCAL_SCORE";
  private static final String COLUMN_GAME1_SCORE = "GAME1_SCORE";
  private static final String COLUMN_GAME2_SCORE = "GAME2_SCORE";
  private static final String COLUMN_GAME3_SCORE = "GAME3_SCORE";
  private static final String COLUMN_LIVES = "LIVES";
  private static final String DATABASE_NAME = "userInfo.db";
  private static final String TABLE_NAME = "USERS";
  private static final String COLUMN_USERNAME = "USERNAME";
  private static final String COLUMN_PASSWORD = "PASSWORD";
  private static final String COLUMN_LEVEL = "LEVEL";
  private static final String COLUMN_GAME = "GAME";
  private static final String CREATE_TABLE =
      "CREATE TABLE USERS ("
          + "ID integer primary key autoincrement, "
          + COLUMN_USERNAME
          + " text, "
          + COLUMN_PASSWORD
          + " text, "
          + COLUMN_LEVEL
          + " integer, "
          + COLUMN_GAME
          + " integer, "
          + COLUMN_SCORE
          + " integer, "
          + COLUMN_LOCAL_SCORE
          + " integer, "
          + COLUMN_GAME1_SCORE
          + " integer, "
          + COLUMN_GAME2_SCORE
          + " integer, "
          + COLUMN_GAME3_SCORE
          + " integer, "
          + COLUMN_LIVES
          + " integer)";
  private SQLiteDatabase database = this.getWritableDatabase();

  public DBHelper(Context context) {
    super(context, DATABASE_NAME, null, 1);
  }

  /** These getters are to get the column names of this database. */
  public static String getTableName() {
    return TABLE_NAME;
  }

  public static String getColumnUsername() {
    return COLUMN_USERNAME;
  }

  public static String getColumnPassword() {
    return COLUMN_PASSWORD;
  }

  public static String getColumnLevel() {
    return COLUMN_LEVEL;
  }

  public static String getColumnGame() {
    return COLUMN_GAME;
  }

  public static String getColumnLocalScore() {
    return COLUMN_LOCAL_SCORE;
  }

  public static String getColumnGame1Score() {
    return COLUMN_GAME1_SCORE;
  }

  public static String getColumnGame2Score() {
    return COLUMN_GAME2_SCORE;
  }

  public static String getColumnGame3Score() {
    return COLUMN_GAME3_SCORE;
  }

  public static String getColumnScore() {
    return COLUMN_SCORE;
  }

  public static String getColumnLives() {
    return COLUMN_LIVES;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }

  @Override
  public synchronized void close() {
    if (this.getWritableDatabase() != null) {
      this.getWritableDatabase().close();
      super.close();
    }
  }

  /**
   * @param username specify a user.
   * @return A Cursor that has the information of this user.
   */
  public Cursor queryData(String username) {
    return database.query(
        "USERS", null, "USERNAME=?", new String[] {username}, null, null, null, null);
  }

  /**
   * @param username specify a user
   * @param keyword the name of the single column.
   * @return A String whose value related to the keyword.
   */
  public String queryData(String username, String keyword) {
    try (Cursor result =
        database.query(
            "USERS", null, "USERNAME=?", new String[] {username}, null, null, null, null)) {
      if (result.getCount() == 0) {
        result.close();
        return null;
      } else {
        result.moveToNext();
        String s = result.getString(result.getColumnIndex(keyword));
        result.close();
        return s;
      }
    }
  }

  /**
   * @param username specify a user.
   * @param password Insert a user with username and password and set default other information.
   */
  public void insertUser(String username, String password) {
    String sql =
        "INSERT INTO "
            + TABLE_NAME
            + " ("
            + COLUMN_USERNAME
            + ","
            + COLUMN_PASSWORD
            + ") "
            + "VALUES('"
            + username
            + "','"
            + password
            + "')";
    database.execSQL(sql);
    updateData(username, COLUMN_LEVEL, 0);
    updateData(username, COLUMN_GAME, 0);
    updateData(username, COLUMN_SCORE, 0);
    updateData(username, COLUMN_LOCAL_SCORE, 0);
    updateData(username, COLUMN_GAME1_SCORE, 100);
    updateData(username, COLUMN_GAME2_SCORE, 0);
    updateData(username, COLUMN_GAME3_SCORE, 0);
    updateData(username, COLUMN_LIVES, 1);
  }

  /**
   * @param username specify a user,
   * @param keyword specified keyword,
   * @param updateVal value of the specified keyword,
   * @param <T> Type of the data which wanted to be updated.
   */
  public <T> void updateData(String username, String keyword, T updateVal) {
    String sql =
        "UPDATE "
            + TABLE_NAME
            + " SET "
            + keyword
            + " = "
            + updateVal.toString()
            + " WHERE "
            + COLUMN_USERNAME
            + " = '"
            + username
            + "'";
    database.execSQL(sql);
  }

  /** @return A cursor which can access to all the information in this table. */
  public Cursor getAllData() {
    return database.rawQuery("select * from " + TABLE_NAME, null);
  }

  /** @param username Reset the data of the specified user. */
  public void resetData(String username) {
    updateData(username, COLUMN_LEVEL, 0);
    updateData(username, COLUMN_GAME, 0);
    updateData(username, COLUMN_LOCAL_SCORE, 0);
    updateData(username, COLUMN_GAME1_SCORE, 100);
    updateData(username, COLUMN_GAME2_SCORE, 0);
    updateData(username, COLUMN_GAME3_SCORE, 0);
    updateData(username, COLUMN_LIVES, 1);
  }
}
