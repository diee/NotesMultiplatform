package com.example.notesmultiplatform.di

import com.example.notesmultiplatform.notes.domain.NotesDataSource

expect class AppModule {
    val notesDataSource: NotesDataSource
}