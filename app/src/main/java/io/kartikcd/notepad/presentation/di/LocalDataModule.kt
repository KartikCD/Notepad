package io.kartikcd.notepad.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.data.local.NotesDAO
import io.kartikcd.notepad.data.repository.datasource.NotepadLocalDataSource
import io.kartikcd.notepad.data.repository.datasourceimpl.NotepadLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(notesDAO: NotesDAO): NotepadLocalDataSource {
        return NotepadLocalDataSourceImpl(notesDAO)
    }
}