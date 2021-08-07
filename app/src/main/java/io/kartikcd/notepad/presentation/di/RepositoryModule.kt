package io.kartikcd.notepad.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.data.repository.NotepadRepositoryImpl
import io.kartikcd.notepad.data.repository.datasource.NotepadLocalDataSource
import io.kartikcd.notepad.domain.repository.NotepadRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNotepadRepository(
        notepadLocalDataSource: NotepadLocalDataSource
    ): NotepadRepository {
        return NotepadRepositoryImpl(notepadLocalDataSource)
    }
}