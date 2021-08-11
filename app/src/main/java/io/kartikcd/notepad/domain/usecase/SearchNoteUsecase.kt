package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.repository.NotepadRepository
import kotlinx.coroutines.flow.Flow

class SearchNoteUsecase(private val notepadRepository: NotepadRepository) {
    fun execute(searchQuery: String): Flow<List<Note>> {
        return notepadRepository.getSearchNotes(searchQuery)
    }
}