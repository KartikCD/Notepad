package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.repository.NotepadRepository
import kotlinx.coroutines.flow.Flow

class ListNotesUsecase(private val notepadRepository: NotepadRepository) {
    fun execute(): Flow<List<Note>> {
        return notepadRepository.getAllNotes()
    }
}