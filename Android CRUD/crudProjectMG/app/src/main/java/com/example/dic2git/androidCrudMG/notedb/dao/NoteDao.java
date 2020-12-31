package com.example.dic2git.androidCrudMG.notedb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dic2git.androidCrudMG.notedb.model.Note;
import com.example.dic2git.androidCrudMG.util.Constants;

import java.util.List;


@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_NOTE)
    List<Note> getNotes();


    @Insert
    long insertNote(Note note);


    @Update
    void updateNote(Note repos);

    @Delete
    void deleteNote(Note note);


    @Delete
    void deleteNotes(Note... note);

}
