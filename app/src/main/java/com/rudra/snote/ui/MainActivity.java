package com.rudra.snote.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.rudra.snote.DbHelper.DbHelper;
import com.rudra.snote.R;
import com.rudra.snote.adapters.NotesAdapter;
import com.rudra.snote.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;

    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private List<Notes> notesList;
    public static final int REQUEST_CODE_AND_NOTE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNote.class),REQUEST_CODE_AND_NOTE
                );
            }
        });

        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
        ));
        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList);
        notesRecyclerView.setAdapter(notesAdapter);

        getNote();

    }

    private void getNote() {

        dbHelper = new DbHelper(this);
        List<Notes> notes = dbHelper.getAllNotes();
//        for (Notes nt : notes) {
//            String log = "Title: " + nt.getTitle() + " ,SubTitle: " + nt.getSubTitle() + " ,NoteText: " +
//                    nt.getNoteText();
//            // Notes to log
//            Log.d("NOTES", log);
////        }
        if (notesList.size() == 0){
            notesList.addAll(notes);
            notesAdapter.notifyDataSetChanged();
        }else
        {
            notesList.add(0,notes.get(0));
            notesAdapter.notifyDataSetChanged();
        }
        notesRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AND_NOTE &&  resultCode== RESULT_OK){
                getNote();
        }
    }
}
