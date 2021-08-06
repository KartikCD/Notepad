package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.repository.NotepadRepository

class CreateNoteUsecase(private val notepadRepository: NotepadRepository) {
    suspend fun execute(note: Note) {
        notepadRepository.createNotes(note)
    }
}