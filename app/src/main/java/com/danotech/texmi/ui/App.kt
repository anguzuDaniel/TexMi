package com.danotech.texmi.ui

import androidx.compose.runtime.Composable
import com.danotech.texmi.ui.appBar.TexMiAppBar
import com.danotech.texmi.ui.chatRoom.ChatScreen

@Composable
fun App() {
    TexMiAppBar(

    ) {
        ChatScreen()
    }
}