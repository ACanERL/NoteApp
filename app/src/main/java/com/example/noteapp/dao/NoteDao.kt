package com.example.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note

@Dao
interface NoteDao {
 @Query("SELECT*FROM note_table")
 fun getNotes():LiveData<List<Note>>
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun insertNotes(note: Note)
 @Query("DELETE FROM note_table WHERE id=:id")
 fun deleteNotes(id:Int)
 @Update
 fun updateNotes(note: Note)

 @Query("SELECT * FROM note_table WHERE priority=3")
 fun getHighNote():LiveData<List<Note>>

 @Query("SELECT * FROM note_table WHERE priority=2")
 fun getMediumNote():LiveData<List<Note>>

 @Query("SELECT * FROM note_table WHERE priority=1")
 fun getLowNote():LiveData<List<Note>>
}