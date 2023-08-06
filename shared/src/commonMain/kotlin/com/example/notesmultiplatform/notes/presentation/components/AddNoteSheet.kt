package com.example.notesmultiplatform.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notesmultiplatform.notes.domain.Note
import com.example.notesmultiplatform.notes.presentation.NotesEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteSheet(
    isOpen: Boolean,
    note: Note? = null,
    onEvent: (NotesEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val isEdit = note?.id != null

    BottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = {
                    onEvent(NotesEvent.OnDismissAddNoteSheet)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }

            if (isEdit) {
                IconButton(
                    onClick = {
                        onEvent(NotesEvent.OnDeleteNote)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))
            TextField(
                value = note?.title ?: "",
                onValueChange = {
                    onEvent(NotesEvent.OnEnteredTitle(it))
                },
                label = {
                    Text(text = "Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
            TextField(
                value = note?.content ?: "",
                onValueChange = {
                    onEvent(NotesEvent.OnEnteredContent(it))
                },
                label = {
                    Text(text = "Content")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                onEvent(NotesEvent.SaveNote)
            }) {
                Text(text = "Save")
            }
        }
    }
}