package com.danotech.texmi.ui.chatRoom

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danotech.texmi.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatRoomViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<ChatRoomUiState> =
        MutableStateFlow(ChatRoomUiState.Initial)
    val uiState: StateFlow<ChatRoomUiState> =
        _uiState.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro-vision",
        apiKey = BuildConfig.apiKey
    )

    fun sendPrompt(
        bitmap: Bitmap,
        prompt: String
    ) {
        _uiState.value = ChatRoomUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        image(bitmap)
                        text(prompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = ChatRoomUiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = ChatRoomUiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}