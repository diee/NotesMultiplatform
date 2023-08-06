package com.example.notesmultiplatform.di

import android.content.Context
import com.example.notesmultiplatform.core.DatabaseDriverFactory
import com.example.notesmultiplatform.database.NoteDatabase
import com.example.notesmultiplatform.notes.data.SqlDelightNoteDataSource
import com.example.notesmultiplatform.notes.domain.NotesDataSource

actual class AppModule(
    private val context: Context
) {
    actual val notesDataSource: NotesDataSource by lazy {
        SqlDelightNoteDataSource(
            db = NoteDatabase(
                driver = DatabaseDriverFactory(context).create(),
            )
        )
    }
}