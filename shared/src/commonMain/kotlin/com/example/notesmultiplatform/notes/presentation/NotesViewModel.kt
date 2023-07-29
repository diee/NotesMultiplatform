package com.example.notesmultiplatform.notes.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.notesmultiplatform.notes.domain.Note
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel() : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    var newNote: Note? by mutableStateOf(null)
        private set

    private val itemsList = listOf(
        Note(0L, "Title 1", "Content 1"),
        Note(1L, "Title 2", "Content 2"),
        Note(2L, "Title 3", "Content 3"),
        Note(3L, "Title 4", "Content 4"),
        Note(4L, "Title 5", "Content 5"),
    )

    init {
        _state.value = NotesState(itemsList)
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.OnAddNoteClick -> {
                _state.update {
                    it.copy(isAddNoteSheetOpen = true)
                }
                newNote = Note(
                    id = null,
                    title = "",
                    content = ""
                )
            }

            NotesEvent.OnDismissAddNoteSheet -> {
                _state.update {
                    it.copy(isAddNoteSheetOpen = false)
                }
            }

            is NotesEvent.OnEnteredTitle -> {
                newNote = newNote?.copy(title = event.title)
            }

            is NotesEvent.OnEnteredContent -> {
                newNote = newNote?.copy(content = event.content)
            }

            NotesEvent.SaveNote -> {
                newNote?.let { note ->
                    val newItemsList = itemsList.toMutableList()
                    newItemsList.add(note)
                    _state.update {
                        it.copy(
                            notes = newItemsList,
                            isAddNoteSheetOpen = false
                        )
                    }
                }
            }

            is NotesEvent.EditNote -> {

            }
        }
    }


}