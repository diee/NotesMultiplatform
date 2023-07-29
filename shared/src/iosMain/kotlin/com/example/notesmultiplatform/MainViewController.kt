package com.example.notesmultiplatform

import androidx.compose.ui.window.ComposeUIViewController

/**
 * Used by the iOS app to create the root view controller.
 * [MainViewControllerKt.MainViewController()]
 * */
fun MainViewController() = ComposeUIViewController {
    App()
}