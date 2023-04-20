package com.example.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val title:String,
    val subtitle:String,
    val notes:String,
    val date:String,
    val priority:String,
    var bgcolor:String?=null
    ):java.io.Serializable