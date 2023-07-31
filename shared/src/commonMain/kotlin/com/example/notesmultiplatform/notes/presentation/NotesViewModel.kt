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

    private var itemsList = mutableListOf<Note>()

    init {
        _state.value = NotesState(itemsList)
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.OnAddNoteClick -> {
                _state.update {
                    it.copy(isAddEditNoteSheetOpen = true)
                }
                newNote = Note(
                    id = itemsList.size.plus(1).toLong(),
                    title = "",
                    content = ""
                )
            }

            NotesEvent.OnDismissAddNoteSheet -> {
                _state.update {
                    it.copy(isAddEditNoteSheetOpen = false)
                }
            }

            is NotesEvent.OnEnteredTitle -> {
                newNote = newNote?.copy(title = event.title)
            }

            is NotesEvent.OnEnteredContent -> {
                newNote = newNote?.copy(content = event.content)
            }

            NotesEvent.SaveNote -> {
                newNote?.let { editedNote ->
                    val indexOfCurrentNote = itemsList.indexOfFirst { it.id == editedNote.id }
                    if (indexOfCurrentNote != -1) {
                        itemsList[indexOfCurrentNote] = editedNote
                    } else {
                        itemsList.add(editedNote)
                    }
                    _state.update {
                        it.copy(
                            notes = itemsList,
                            isAddEditNoteSheetOpen = false
                        )
                    }
                }
            }

            is NotesEvent.SelectNote -> {
                newNote = event.note
                _state.update {
                    it.copy(
                        isAddEditNoteSheetOpen = true
                    )
                }
            }
        }
    }


}