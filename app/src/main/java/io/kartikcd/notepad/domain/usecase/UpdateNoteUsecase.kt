package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.repository.NotepadRepository

class UpdateNoteUsecase(private val notepadRepository: NotepadRepository) {
    suspend fun execute(note: Note) {
        return notepadRepository.updateNote(note)
    }
}