package io.kartikcd.notepad.data.repository.datasourceimpl

import io.kartikcd.notepad.data.local.NotesDAO
import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.data.repository.datasource.NotepadLocalDataSource
import kotlinx.coroutines.flow.Flow

class NotepadLocalDataSourceImpl(
    private val notesDAO: NotesDAO
): NotepadLocalDataSource {
    override suspend fun createNotes(note: Note) {
        notesDAO.insert(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return notesDAO.getAllNotes()
    }

    override fun getHighPriorityNotes(): Flow<List<Note>> {
        return notesDAO.getHighPriorityNotes()
    }

    override fun getLowPriorityNotes(): Flow<List<Note>> {
        return notesDAO.getLowPriorityNotes()
    }

    override suspend fun updateNote(note: Note) {
        notesDAO.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDAO.deleteNote(note)
    }

    override fun getSearchNotes(searchQuery: String): Flow<List<Note>> {
        return notesDAO.searchDatabase(searchQuery)
    }
}