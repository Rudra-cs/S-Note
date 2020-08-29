package com.rudra.snote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.rudra.snote.model.Notes;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "note_info_list";
    private static final String COL_ID = "id";
    private static final String COL_NOTE_TITLE = "note_title";
    private static final String COL_NOTE_SUBTITLE = "note_subtitle";
    private static final String COL_NOTE_TEXT = "note_text";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NOTE_TITLE +
            " TEXT," + COL_NOTE_SUBTITLE + " TEXT," + COL_NOTE_TEXT + " TEXT)";

    public DbHelper(@Nullable Context context) {
        super(context, "Notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataToDatabase (SQLiteDatabase database, Notes notes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTE_TITLE,notes.getTitle());
        contentValues.put(COL_NOTE_SUBTITLE,notes.getSubTitle());
        contentValues.put(COL_NOTE_TEXT,notes.getNoteText());

        database.insert(TABLE_NAME,null,contentValues);
        Log.d("dbrudra","successfully Inserted");
        database.close();
    }
}
