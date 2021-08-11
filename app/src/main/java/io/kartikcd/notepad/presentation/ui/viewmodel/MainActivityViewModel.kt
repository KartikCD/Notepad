package io.kartikcd.notepad.presentation.ui.viewmodel

import androidx.lifecycle.*
import io.kartikcd.notepad.data.models.Note
import io.kartikcd.notepad.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val createNoteUsecase: CreateNoteUsecase,
    private val deleteNoteUsecase: DeleteNoteUsecase,
    private val listHighPriorityNoteUsecase: ListHighPriorityNoteUsecase,
    private val listLowPriorityNoteUsecase: ListLowPriorityNoteUsecase,
    private val listNotesUsecase: ListNotesUsecase,
    private val updateNoteUsecase: UpdateNoteUsecase,
    private val searchNoteUsecase: SearchNoteUsecase,
    private val deleteAllNotesUsecase: DeleteAllNotesUsecase,
    private val getNoteByIdUsecase: GetNoteByIdUsecase
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    fun getNoteByIdFromLocalDatabase(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getNoteByIdUsecase.execute(id)
            _note.postValue(response)
        }
    }

    fun updateNoteToLocalDatabase(note: Note) {
        viewModelScope.launch {
            updateNoteUsecase.execute(note)
        }
    }

    fun saveNotesToLocalDatabase(note: Note) {
        viewModelScope.launch {
            createNoteUsecase.execute(note)
        }
    }

    fun fetchNotesFromLocalDatabase() =
        liveData {
            listNotesUsecase.execute().collect {
                emit(it)
            }
        }

    fun fetchHighPriorityNotesFromLocalDatabase() =
        liveData {
            listHighPriorityNoteUsecase.execute().collect {
                emit(it)
            }
        }

    fun fetchLowPriorityNotesFromLocalDatabase() =
        liveData {
            listLowPriorityNoteUsecase.execute().collect {
                emit(it)
            }
        }

    fun fetchSearchNotesFromLocalDatabase(searchQuery: String) =
        liveData {
            searchNoteUsecase.execute(searchQuery).collect {
                emit(it)
            }
        }

    fun deleteNoteFromLocalDatabase(note: Note) {
        viewModelScope.launch {
            deleteNoteUsecase.execute(note)
        }
    }

    fun deleteAllNotesFromLocalDatabase() {
        viewModelScope.launch {
            deleteAllNotesUsecase.execute()
        }
    }
}