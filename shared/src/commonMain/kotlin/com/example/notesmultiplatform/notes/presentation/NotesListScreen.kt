package com.example.notesmultiplatform.notes.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesmultiplatform.notes.domain.Note
import com.example.notesmultiplatform.notes.presentation.components.AddEditNoteSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    state: NotesState,
    newNote: Note?,
    onEvent: (NotesEvent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(NotesEvent.OnAddNoteClick)
                },
            ) {
                Text(text = "Add")
            }
        }
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(state.notes) { note ->
                NoteComponent(
                    note = note,
                    onClick = { onEvent(NotesEvent.SelectNote(note)) })
            }
            if (state.notes.isEmpty()) {
                item {
                    Text(
                        text = "No notes",
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

    AddEditNoteSheet(
        isOpen = state.isAddEditNoteSheetOpen,
        note = newNote,
        onEvent = onEvent,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NoteComponent(
    note: Note,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.content, style = MaterialTheme.typography.bodySmall)
        }
    }
}