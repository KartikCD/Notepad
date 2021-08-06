package io.kartikcd.notepad.data.repository

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.data.repository.datasource.NotepadLocalDataSource
import io.kartikcd.notepad.domain.repository.NotepadRepository
import kotlinx.coroutines.flow.Flow

class NotepadRepositoryImpl(
    private val notepadLocalDataSource: NotepadLocalDataSource
): NotepadRepository {

    override suspend fun createNotes(note: Note) {
        notepadLocalDataSource.createNotes(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return notepadLocalDataSource.getAllNotes()
    }

    override fun getHighPriorityNotes(): Flow<List<Note>> {
        return notepadLocalDataSource.getHighPriorityNotes()
    }

    override fun getLowPriorityNotes(): Flow<List<Note>> {
        return notepadLocalDataSource.getLowPriorityNotes()
    }

    override suspend fun updateNote(note: Note) {
        notepadLocalDataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notepadLocalDataSource.deleteNote(note)
    }
}