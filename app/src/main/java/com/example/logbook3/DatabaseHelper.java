package com.example.logbook3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact_details";
    private static final String ID_COLUMN_NAME = "person_id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String DOB_COLUMN_NAME = "dob";
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String AVATAR_COLUMN_NAME = "avatar";
    private SQLiteDatabase database;
    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT, " +
            "%s TEXT, " +
            "%s TEXT, " +
            "%s INTEGER)",
            DATABASE_NAME, ID_COLUMN_NAME, NAME_COLUMN_NAME, DOB_COLUMN_NAME, EMAIL_COLUMN_NAME, AVATAR_COLUMN_NAME);
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " + newVersion + " - old data lost");
    }

    public long insertDetails(String name, String dob, String email, int avatar) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN_NAME, name);
        rowValues.put(DOB_COLUMN_NAME, dob);
        rowValues.put(EMAIL_COLUMN_NAME, email);
        rowValues.put(AVATAR_COLUMN_NAME, avatar);
        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }
    public List<User> getDetails() {
        List<User> userList = new ArrayList<>();

        Cursor results = database.query(DATABASE_NAME, new String[] {"person_id", "name", "dob", "email", "avatar"}, null, null, null, null, "name");
        results.moveToFirst();

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);
            int avatar = results.getInt(4);

            userList.add(new User(name, dob, email, avatar));
            results.moveToNext();
        }
        results.close();
        return userList;
    }
}
