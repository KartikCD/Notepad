package io.kartikcd.notepad.data.local

import androidx.room.*
import io.kartikcd.notepad.data.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes order by id desc")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes order by priority desc, name")
    fun getHighPriorityNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes order by priority asc, name")
    fun getLowPriorityNotes(): Flow<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getNoteById(id: Int): Note
}