package io.kartikcd.notepad.domain.usecase

import io.kartikcd.notepad.domain.repository.NotepadRepository

class DeleteAllNotesUsecase(private val notepadRepository: NotepadRepository) {
    suspend fun execute() {
        notepadRepository.deleteAllNotes()
    }
}