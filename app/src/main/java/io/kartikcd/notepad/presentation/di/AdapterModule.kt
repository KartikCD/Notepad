package io.kartikcd.notepad.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.presentation.adapter.NotesListAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Provides
    @Singleton
    fun provideNotesListAdapter(): NotesListAdapter {
        return NotesListAdapter()
    }
}