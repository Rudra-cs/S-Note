package com.rudra.snote.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rudra.snote.DbHelper.DbHelper;
import com.rudra.snote.R;
import com.rudra.snote.model.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteSubtitle,inputNoteText;
    private TextView textDateTime;
    ImageView imageBack;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        dbHelper = new DbHelper(this);
        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note Title cannot be Empty!", Toast.LENGTH_SHORT).show();
        } else if (inputNoteSubtitle.getText().toString().trim().isEmpty()
                && inputNoteText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note cannot be Empty!", Toast.LENGTH_SHORT).show();
        }

        final Notes notes = new Notes();
        inputNoteTitle.setText(inputNoteTitle.getText().toString());
        inputNoteSubtitle.setText(inputNoteSubtitle.getText().toString());
        textDateTime.setText(textDateTime.getText().toString());
        inputNoteText.setText(inputNoteText.getText().toString());

        notes.setTitle(inputNoteTitle.getText().toString());
        notes.setSubTitle(inputNoteSubtitle.getText().toString());
        notes.setDateTime(textDateTime.getText().toString());
        notes.setNoteText(inputNoteText.getText().toString());

        dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(),notes);

        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }
}
