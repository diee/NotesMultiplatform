package com.example.notesmultiplatform.notes.presentation

import com.example.notesmultiplatform.notes.domain.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val isAddEditNoteSheetOpen: Boolean = false,
    val noteTitle: String? = null,
    val noteContent: String? = null
)
