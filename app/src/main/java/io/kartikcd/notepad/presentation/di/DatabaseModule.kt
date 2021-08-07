package io.kartikcd.notepad.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.kartikcd.notepad.data.local.NotesDAO
import io.kartikcd.notepad.data.local.NotesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesNotepadDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "notepad_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNotesDAO(notesDatabase: NotesDatabase): NotesDAO {
        return notesDatabase.getNotesDAO()
    }
}