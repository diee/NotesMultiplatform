package com.example.notesmultiplatform.notes.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.notesmultiplatform.notes.domain.Note
import com.example.notesmultiplatform.notes.domain.NotesDataSource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesDataSource: NotesDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())

    val state = combine(
        _state,
        notesDataSource.getNotes()
    ) { state, notes ->
        state.copy(
            notes = notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), NotesState())

    var note: Note? by mutableStateOf(null)
        private set

    fun onEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.OnAddNoteClick -> {
                _state.update {
                    it.copy(isAddEditNoteSheetOpen = true)
                }
                note = Note(id = null, title = "", content = "")
            }

            NotesEvent.OnDismissAddNoteSheet -> {
                _state.update {
                    it.copy(isAddEditNoteSheetOpen = false)
                }
            }

            is NotesEvent.OnEnteredTitle -> {
                note = note?.copy(title = event.title)
            }

            is NotesEvent.OnEnteredContent -> {
                note = note?.copy(content = event.content)
            }

            NotesEvent.SaveNote -> {
                viewModelScope.launch {
                    note?.let {
                        notesDataSource.addOrUpdate(it)
                    }
                }

                _state.update {
                    it.copy(isAddEditNoteSheetOpen = false)
                }
            }

            is NotesEvent.SelectNote -> {
                note = event.note
                _state.update {
                    it.copy(isAddEditNoteSheetOpen = true)
                }
            }

            is NotesEvent.OnDeleteNote -> {
                viewModelScope.launch {
                    note?.id?.let {
                        notesDataSource.remove(it)
                    }
                    _state.update {
                        it.copy(isAddEditNoteSheetOpen = false)
                    }
                }
            }
        }
    }


}