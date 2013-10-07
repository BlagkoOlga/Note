package com.example.MyNotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Olka on 06.10.13.
 */
public class CreateAdapter {
    SQLiteDatabase database;
    CreateOpenHelper openHelper;
    Context context;
    String[] cols = {openHelper.KEY_ID, openHelper.TITLE, openHelper.NOTE};

    public CreateAdapter (Context c){
        context = c;
    }

    public CreateAdapter openToRead(){
        openHelper = new CreateOpenHelper(context, openHelper.DATABASE_NAME, null, openHelper.VERSION);
        database = openHelper.getReadableDatabase();
        return this;
    }

    public CreateAdapter openToWrite(){
        openHelper = new CreateOpenHelper(context, openHelper.DATABASE_NAME, null, openHelper.VERSION);
        database = openHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        database.close();
    }

    public long insertData(String title, String note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(openHelper.TITLE, title);
        contentValues.put(openHelper.NOTE, note);
        openToWrite();
        long val = database.insert(openHelper.TABLE_NAME, null, contentValues);
        close();
        return val;
    }

    public Cursor queryNote(){
        openToWrite();
        Cursor c = database.query(openHelper.TABLE_NAME, cols, null,null, null, null, null);
        return c;
    }

    public Cursor queryAll(int noteId){
        openToWrite();
        Cursor c = database.query(openHelper.TABLE_NAME, cols,openHelper.KEY_ID + "=" + noteId, null, null, null, null);
        return c;
    }

    public long update(int rowId, String title, String note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(openHelper.TITLE, title);
        contentValues.put(openHelper.NOTE, note);
        openToWrite();
        long val = database.update(openHelper.TABLE_NAME, contentValues,
                openHelper.KEY_ID + "=" + rowId, null);
        close();
        return val;
    }

    public int deletOneRecord(int rowId) {
        openToWrite();
        int val = database.delete(openHelper.TABLE_NAME,
                openHelper.KEY_ID + "=" + rowId, null);
        close();
        return val;
    }

}
