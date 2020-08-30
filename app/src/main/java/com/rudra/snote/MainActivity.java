package com.rudra.snote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.rudra.snote.model.Notes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_AND_NOTE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNote.class),REQUEST_CODE_AND_NOTE
                );
            }
        });
        ArrayList<Notes> notes = dbHelper.getAllNotes();
        for (Notes nt : notes) {
            String log = "Title: " + nt.getTitle() + " ,SubTitle: " + nt.getSubTitle() + " ,NoteText: " +
                    nt.getNoteText();
            // Notes to log
            Log.d("NOTES: ", log);
        }
//        Log.d("NOTES",notes.toString());
    }

}
