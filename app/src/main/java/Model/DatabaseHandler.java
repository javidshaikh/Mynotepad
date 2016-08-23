package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


import Data.MyNotes;

/**
 * Created by Mr.Robot on 8/4/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mynotesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes_table";
    private static final String NOTEE_TITLE = "title";
    private static final String NOTEE_CONTENT = "content";
    private static final String KEY_ID = "_id";
    private final ArrayList<MyNotes> notesList = new ArrayList<>();


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String  CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + NOTEE_TITLE + " TEXT, "

                + NOTEE_CONTENT + " TEXT);" ;
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void deleteNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void addnotes(MyNotes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTEE_TITLE, notes.getTitle());
        contentValues.put(NOTEE_CONTENT, notes.getNotes());
        db.insert(TABLE_NAME, null, contentValues);

        Log.v("Added","OK");
        db.close();
    }

    public ArrayList<MyNotes> getNotes(){

        notesList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, NOTEE_TITLE, NOTEE_CONTENT}, null, null, null, null, KEY_ID + " DESC ");

        if (cursor.moveToFirst()){
            do {
                MyNotes myNotes  = new MyNotes();
                myNotes.setNotes(cursor.getString(cursor.getColumnIndex(NOTEE_CONTENT)));
                myNotes.setTitle(cursor.getString(cursor.getColumnIndex(NOTEE_TITLE)));
                myNotes.setNotesID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                notesList.add(myNotes);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  notesList;
    }
}
