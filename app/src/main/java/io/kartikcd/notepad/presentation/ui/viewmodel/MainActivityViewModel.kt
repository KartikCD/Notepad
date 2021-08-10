package io.kartikcd.notepad.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.usecase.*
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val createNoteUsecase: CreateNoteUsecase,
    private val deleteNoteUsecase: DeleteNoteUsecase,
    private val listHighPriorityNoteUsecase: ListHighPriorityNoteUsecase,
    private val listLowPriorityNoteUsecase: ListLowPriorityNoteUsecase,
    private val listNotesUsecase: ListNotesUsecase,
    private val updateNoteUsecase: UpdateNoteUsecase
): ViewModel() {
    fun saveNotesToLocalDatabase(note: Note) {
        viewModelScope.launch {
            createNoteUsecase.execute(note)
        }
    }
}