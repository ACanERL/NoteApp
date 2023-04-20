package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteapp.dao.NoteDao
import com.example.noteapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getNotes()
    }

    fun getHighNote():LiveData<List<Note>> =noteDao.getHighNote()
    fun getMediumNote():LiveData<List<Note>> =noteDao.getMediumNote()
    fun getLowNote():LiveData<List<Note>> =noteDao.getLowNote()

    fun insertNotes(note: Note){
        noteDao.insertNotes(note)
    }
    fun deleteNotes(id:Int){
        noteDao.deleteNotes(id)
    }
    fun updateNotes(note: Note){
        noteDao.updateNotes(note)
    }
}