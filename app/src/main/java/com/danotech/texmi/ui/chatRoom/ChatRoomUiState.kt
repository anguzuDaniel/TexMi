package com.danotech.texmi.ui.chatRoom

/**
 * A sealed hierarchy describing the state of the text generation.
 */
sealed interface ChatRoomUiState {

    /**
     * Empty state when the screen is first shown
     */
    object Initial : ChatRoomUiState

    /**
     * Still loading
     */
    object Loading : ChatRoomUiState

    /**
     * Text has been generated
     */
    data class Success(val outputText: String) : ChatRoomUiState

    /**
     * There was an error generating text
     */
    data class Error(val errorMessage: String) : ChatRoomUiState
}