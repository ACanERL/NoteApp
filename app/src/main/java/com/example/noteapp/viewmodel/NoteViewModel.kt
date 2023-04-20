package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
 val repository:NoteRepository
 init {
   val dao=NoteDatabase.getDatabaseInstance(application).noteDao()
   repository=NoteRepository(dao)
 }
    fun addNote(note: Note){
        repository.insertNotes(note)
    }
    fun deleteNote(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNote(note: Note){
        repository.updateNotes(note)
    }
    fun getAllNote():LiveData<List<Note>> =repository.getAllNotes()


    fun getHighNote():LiveData<List<Note>> =repository.getHighNote()
    fun getMediumNote():LiveData<List<Note>> =repository.getMediumNote()
    fun getLowNote():LiveData<List<Note>> =repository.getLowNote()
}