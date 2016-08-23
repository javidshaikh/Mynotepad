package com.example.mrrobot.mynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Data.MyNotes;
import Model.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private EditText Title, Notes;
    private Button List, Save;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(MainActivity.this);
        Title = (EditText) findViewById(R.id.title);
        Notes = (EditText) findViewById(R.id.notes);
        Save = (Button) findViewById(R.id.save);
        List = (Button) findViewById(R.id.saved);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveDataToDB();
            }
        });
    }

    private void SaveDataToDB() {

        MyNotes notes = new MyNotes();

        String title = Title.getText().toString().trim();
        String note = Notes.getText().toString().trim();

        if (title.equals("") || note.equals("")){
            Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_SHORT).show();
        }else {
            notes.setTitle(title);
            notes.setNotes(note);
            db.addnotes(notes);
            db.close();
            Title.setText("");
            Notes.setText("");
            startActivity(new Intent(MainActivity.this, NotesList.class));


        }
    }
}
