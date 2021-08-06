package io.kartikcd.notepad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.kartikcd.notepad.data.models.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getNotesDAO(): NotesDAO
}