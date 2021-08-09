package io.kartikcd.notepad.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import io.kartikcd.notepad.data.local.NotesDAO
import io.kartikcd.notepad.data.local.NotesDatabase
import io.kartikcd.notepad.data.models.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class NotesDAOTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: NotesDAO
    private lateinit var database: NotesDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).build()
        dao = database.getNotesDAO()
    }

    @Test
    fun saveNotesToDBTest() {
        val notes = Note(name = "First", body = "First note", priority = true)
        runBlocking {
            dao.insert(notes)
            val allNotes = dao.getAllNotes()
            allNotes.collect {
                Truth.assertThat(it.get(0).name).isEqualTo(notes.name)
            }
        }
    }
}