package com.example.MyNotebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Olka on 06.10.13.
 */
public class CreateOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NOTE_DB";
    public static final String TABLE_NAME = "NOTE_TABLE";
    public static final int VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String TITLE = "TITLE";
    public static final String NOTE = "NOTE";
    public static final String SCRIPT = "create table " + TABLE_NAME + " ("
            + KEY_ID + " integer primary key autoincrement, " + TITLE
            + " text not null, " + NOTE + " text not null );";

    public CreateOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }
}
