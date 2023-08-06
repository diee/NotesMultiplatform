package com.example.notesmultiplatform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.notesmultiplatform.di.AppModule
import com.example.notesmultiplatform.notes.presentation.NotesListScreen
import com.example.notesmultiplatform.notes.presentation.NotesViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App(
    appModule: AppModule
) {

    val viewModel = getViewModel(
        key = "notes-list-screen",
        factory = viewModelFactory {
            NotesViewModel(appModule.notesDataSource)
        }
    )

    val state by viewModel.state.collectAsState()

    NotesListScreen(
        state = state,
        onEvent = viewModel::onEvent,
        newNote = viewModel.note
    )
}