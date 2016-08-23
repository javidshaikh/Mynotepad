package com.example.mrrobot.mynote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Data.MyNotes;
import Model.CustomViewListAdapter;
import Model.DatabaseHandler;

public class NotesList extends AppCompatActivity {

    private DatabaseHandler db;
    private ArrayList<MyNotes> dbnotes = new ArrayList<>();
    private CustomViewListAdapter notesAdpter;
    private ListView listView;
    private MyNotes myNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);


        listView = (ListView) findViewById(R.id.list);
        refreshData();

    }

    private void refreshData() {
        dbnotes.clear();
        db = new DatabaseHandler(getApplicationContext());
        ArrayList<MyNotes> notesFromDB = db.getNotes();

        for (int i =0; i < notesFromDB.size(); i++){
            String name = notesFromDB.get(i).getTitle();
            String notes = notesFromDB.get(i).getNotes();
            int id  = notesFromDB.get(i).getNotesID();
            myNotes = new MyNotes();
            myNotes.setTitle(name);
            myNotes.setNotes(notes);
            myNotes.setNotesID(id);
            dbnotes.add(myNotes);
        }

        db.close();

        notesAdpter = new CustomViewListAdapter(NotesList.this, R.layout.list_row, dbnotes);
        listView.setAdapter(notesAdpter);
        notesAdpter.notifyDataSetChanged();

    }
}
