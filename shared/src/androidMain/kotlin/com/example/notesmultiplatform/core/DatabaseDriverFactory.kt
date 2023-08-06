package com.example.notesmultiplatform.core

import android.content.Context
import com.example.notesmultiplatform.database.NoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            NoteDatabase.Schema,
            context,
            "note.db"
        )
    }
}