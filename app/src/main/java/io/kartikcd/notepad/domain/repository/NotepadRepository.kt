package io.kartikcd.notepad.domain.repository

import io.kartikcd.notepad.data.models.Note
import kotlinx.coroutines.flow.Flow

interface NotepadRepository {
    suspend fun createNotes(note: Note)
    fun getAllNotes(): Flow<List<Note>>
    fun getHighPriorityNotes(): Flow<List<Note>>
    fun getLowPriorityNotes(): Flow<List<Note>>
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getSearchNotes(searchQuery: String): Flow<List<Note>>
}