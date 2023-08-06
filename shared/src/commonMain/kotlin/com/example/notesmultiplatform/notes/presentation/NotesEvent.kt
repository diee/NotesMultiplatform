package com.example.notesmultiplatform.notes.presentation

import com.example.notesmultiplatform.notes.domain.Note

interface NotesEvent {
    object OnAddNoteClick : NotesEvent
    object OnDismissAddNoteSheet : NotesEvent
    class OnEnteredTitle(val title: String) : NotesEvent
    class OnEnteredContent(val content: String) : NotesEvent
    object SaveNote : NotesEvent
    data class SelectNote(val note: Note) : NotesEvent
    object OnDeleteNote : NotesEvent
}