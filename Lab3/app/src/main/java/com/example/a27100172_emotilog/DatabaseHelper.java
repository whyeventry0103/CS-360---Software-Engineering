package com.example.a27100172_emotilog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EmotiLog.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LOGS = "emotion_logs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMOTION = "emotion";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_LOGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMOTION + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGS);
        onCreate(db);
    }

    public boolean addLog(String emotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMOTION, emotion);

        long result = db.insert(TABLE_LOGS, null, values);
        return result != -1;
    }

    public Cursor getAllLogs() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOGS,
                null, null, null, null, null,
                COLUMN_TIMESTAMP + " DESC");
    }

    public Cursor getDailySummary() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT date(" + COLUMN_TIMESTAMP + ") as log_date, "
                + COLUMN_EMOTION + ", COUNT(*) as count "
                + "FROM " + TABLE_LOGS + " "
                + "GROUP BY log_date, " + COLUMN_EMOTION + " "
                + "ORDER BY log_date DESC";
        return db.rawQuery(query, null);
    }
}