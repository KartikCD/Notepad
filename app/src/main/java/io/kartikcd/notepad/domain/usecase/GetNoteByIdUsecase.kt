package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.repository.NotepadRepository

class GetNoteByIdUsecase(private val notepadRepository: NotepadRepository) {
    suspend fun execute(id: Int): Note {
        return notepadRepository.getNotesByID(id)
    }
}