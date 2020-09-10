package com.rudra.snote.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.rudra.snote.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "note_info_list";
    private static final String COL_ID = "id";
    private static final String COL_NOTE_TITLE = "note_title";
    private static final String COL_NOTE_SUBTITLE = "note_subtitle";
    private static final String COL_NOTE_TEXT = "note_text";
    private static final String COL_NOTE_TIME = "note_time_date";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NOTE_TITLE +
            " TEXT," + COL_NOTE_SUBTITLE + " TEXT," + COL_NOTE_TIME + " TEXT," + COL_NOTE_TEXT + " TEXT)";

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
        contentValues.put(COL_NOTE_TIME,notes.getDateTime());
        contentValues.put(COL_NOTE_TEXT,notes.getNoteText());


        database.insert(TABLE_NAME,null,contentValues);
        Log.d("dbrudra","successfully Inserted");
        database.close();
    }

    public ArrayList<Notes> getAllNotes() {
        ArrayList<Notes> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Notes notes = new Notes();
                notes.setTitle(cursor.getString(cursor.getColumnIndex(COL_NOTE_TITLE)));
                notes.setSubTitle(cursor.getString(cursor.getColumnIndex(COL_NOTE_SUBTITLE)));
                notes.setDateTime(cursor.getString(cursor.getColumnIndex(COL_NOTE_TIME)));
                notes.setNoteText(cursor.getString(cursor.getColumnIndex(COL_NOTE_TEXT)));

                notesList.add(notes);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notesList;
    }
}
