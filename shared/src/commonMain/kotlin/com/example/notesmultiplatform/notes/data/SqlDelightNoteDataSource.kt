package com.example.notesmultiplatform.notes.data

import com.example.notesmultiplatform.database.NoteDatabase
import com.example.notesmultiplatform.notes.domain.Note
import com.example.notesmultiplatform.notes.domain.NotesDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightNoteDataSource(
    db: NoteDatabase
) : NotesDataSource {

    private val queries = db.noteQueries

    override fun getNotes(): Flow<List<Note>> {
        return queries
            .getNotes()
            .asFlow()
            .mapToList()
            .map { it.map { noteDB -> noteDB.toEntity() } }
    }

    override suspend fun addOrUpdate(note: Note) {
        queries.insertNoteEntity(
            id = note.id,
            title = note.title,
            content = note.content
        )
    }

    override suspend fun remove(id: Long) {
        queries.deleteNoteEntity(id)
    }
}