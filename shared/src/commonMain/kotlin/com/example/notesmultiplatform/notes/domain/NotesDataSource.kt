package com.example.notesmultiplatform.notes.domain

import kotlinx.coroutines.flow.Flow

interface NotesDataSource {
    fun getNotes(): Flow<List<Note>>
    suspend fun addOrUpdate(note: Note)
    suspend fun remove(id: Long)
}