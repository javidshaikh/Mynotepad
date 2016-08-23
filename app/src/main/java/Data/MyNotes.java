package Data;

import java.io.Serializable;

/**
 * Created by Mr.Robot on 8/4/2016.
 */
public class MyNotes implements Serializable {

    private static final long serialVersionUID = 10L;
    private String Title;
    private String Notes;
    private int NotesID;

    public MyNotes(String title, String notes, int notesID) {
        Title = title;
        Notes = notes;
        NotesID = notesID;
    }

    public MyNotes() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getNotesID() {
        return NotesID;
    }

    public void setNotesID(int notesID) {
        NotesID = notesID;
    }
}
