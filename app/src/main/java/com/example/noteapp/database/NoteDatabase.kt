package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.Converts
import com.example.noteapp.dao.NoteDao
import com.example.noteapp.model.Note

@Database(entities = [Note::class], version = 2, exportSchema = true)
@TypeConverters(Converts::class)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDao

  companion object{
      @Volatile
      var INSTANCE:NoteDatabase?=null

      fun getDatabaseInstance(context: Context):NoteDatabase{
          val instance= INSTANCE
          if(instance!=null){
              return instance
          }
          synchronized(this){
              val roomDatabase=Room.databaseBuilder(context,NoteDatabase::class.java,"notedb").allowMainThreadQueries().build()
              INSTANCE=roomDatabase
              return return  roomDatabase
          }
      }
  }
}