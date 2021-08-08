package io.kartikcd.notepad.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.kartikcd.notepad.domain.usecase.*

class MainActivityViewModel(
    private val createNoteUsecase: CreateNoteUsecase,
    private val deleteNoteUsecase: DeleteNoteUsecase,
    private val listHighPriorityNoteUsecase: ListHighPriorityNoteUsecase,
    private val listLowPriorityNoteUsecase: ListLowPriorityNoteUsecase,
    private val listNotesUsecase: ListNotesUsecase,
    private val updateNoteUsecase: UpdateNoteUsecase
): ViewModel() {
}