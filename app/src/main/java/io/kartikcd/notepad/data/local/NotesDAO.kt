package io.kartikcd.notepad.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.kartikcd.notepad.data.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE priority=1")
    fun getHighPriorityNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE priority!=1")
    fun getLowPriorityNotes(): Flow<List<Note>>
}