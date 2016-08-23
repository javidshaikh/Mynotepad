package com.example.mrrobot.mynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Data.MyNotes;
import Model.DatabaseHandler;

public class NotesDetails extends AppCompatActivity {

    private TextView title, notes;
    private  int noteID;
    private Button Delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        title = (TextView) findViewById(R.id.dtitle);
        notes = (TextView) findViewById(R.id.dnotes);
        Delete = (Button) findViewById(R.id.dsave);


        MyNotes myNotes = (MyNotes) getIntent().getSerializableExtra("userData");
        title.setText(myNotes.getTitle());
        notes.setText(myNotes.getTitle());
        noteID = myNotes.getNotesID();
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.deleteNotes(noteID);
                startActivity(new Intent(NotesDetails.this, NotesList.class));
            }
        });
    }
}
