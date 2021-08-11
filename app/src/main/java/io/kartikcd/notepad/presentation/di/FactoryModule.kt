package io.kartikcd.notepad.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.domain.usecase.*
import io.kartikcd.notepad.presentation.ui.viewmodel.MainActivityViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Provides
    @Singleton
    fun provideMainActivityViewModelFactory(
        createNoteUsecase: CreateNoteUsecase,
        deleteNoteUsecase: DeleteNoteUsecase,
        listHighPriorityNoteUsecase: ListHighPriorityNoteUsecase,
        listLowPriorityNoteUsecase: ListLowPriorityNoteUsecase,
        listNotesUsecase: ListNotesUsecase,
        updateNoteUsecase: UpdateNoteUsecase,
        searchNoteUsecase: SearchNoteUsecase,
        deleteAllNotesUsecase: DeleteAllNotesUsecase
    ): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(
            createNoteUsecase, deleteNoteUsecase, listHighPriorityNoteUsecase, listLowPriorityNoteUsecase, listNotesUsecase, updateNoteUsecase, searchNoteUsecase, deleteAllNotesUsecase
        )
    }
}