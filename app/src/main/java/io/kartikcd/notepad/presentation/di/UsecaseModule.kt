package io.kartikcd.notepad.presentation.di

import androidx.room.Update
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.domain.repository.NotepadRepository
import io.kartikcd.notepad.domain.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {
    @Provides
    @Singleton
    fun provideCreateNoteUsecase(notepadRepository: NotepadRepository): CreateNoteUsecase {
        return CreateNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUsecase(notepadRepository: NotepadRepository): DeleteNoteUsecase {
        return DeleteNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideGetLowPriorityNoteUsecase(notepadRepository: NotepadRepository): ListLowPriorityNoteUsecase {
        return ListLowPriorityNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideGetNoteUsecase(notepadRepository: NotepadRepository): ListNotesUsecase {
        return ListNotesUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideGetHighPriorityNoteUsecase(notepadRepository: NotepadRepository): ListHighPriorityNoteUsecase {
        return ListHighPriorityNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateNoteUsecase(notepadRepository: NotepadRepository): UpdateNoteUsecase {
        return UpdateNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideSearchNoteUsecase(notepadRepository: NotepadRepository): SearchNoteUsecase {
        return SearchNoteUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllNotesUsecase(notepadRepository: NotepadRepository): DeleteAllNotesUsecase {
        return DeleteAllNotesUsecase(notepadRepository)
    }

    @Provides
    @Singleton
    fun provideGetNoteByIdUsecase(notepadRepository: NotepadRepository): GetNoteByIdUsecase {
        return GetNoteByIdUsecase(notepadRepository)
    }
}