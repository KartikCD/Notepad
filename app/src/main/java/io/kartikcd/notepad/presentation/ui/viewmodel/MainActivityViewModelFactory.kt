package io.kartikcd.notepad.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.kartikcd.notepad.domain.usecase.*

class MainActivityViewModelFactory(
    private val createNoteUsecase: CreateNoteUsecase,
    private val deleteNoteUsecase: DeleteNoteUsecase,
    private val listHighPriorityNoteUsecase: ListHighPriorityNoteUsecase,
    private val listLowPriorityNoteUsecase: ListLowPriorityNoteUsecase,
    private val listNotesUsecase: ListNotesUsecase,
    private val updateNoteUsecase: UpdateNoteUsecase,
    private val searchNoteUsecase: SearchNoteUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            createNoteUsecase, deleteNoteUsecase, listHighPriorityNoteUsecase, listLowPriorityNoteUsecase, listNotesUsecase, updateNoteUsecase, searchNoteUsecase
        ) as T
    }
}