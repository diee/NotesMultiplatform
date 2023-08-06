package com.example.notesmultiplatform.notes.data

import com.example.notesmultiplatform.notes.domain.Note
import database.NoteEntity

fun NoteEntity.toEntity(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
    )
}