package com.example.dic2git.androidCrudMG.notedb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.dic2git.androidCrudMG.notedb.dao.NoteDao;
import com.example.dic2git.androidCrudMG.notedb.model.Note;
import com.example.dic2git.androidCrudMG.util.Constants;
import com.example.dic2git.androidCrudMG.util.DateRoomConverter;


@Database(entities = { Note.class }, version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();


    private static NoteDatabase noteDB;

    public static /*synchronized*/ NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        noteDB = null;
    }
}