package com.example.feature_main.presentation.main.ui

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import com.example.core_ui.sharedUi.ui.ErrorMassage
import com.example.core_ui.sharedUi.ui.RoundLoad
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.feature_main.presentation.main.viewModel.CharactersUiState
import com.example.feature_main.presentation.main.viewModel.MainViewModel


@Composable
fun MainControlState(
    charactersUiState: CharactersUiState,
    textFont: TextFont,
    context: Context,
    viewModel: MainViewModel,
    scrollState: ScrollState,
    onClick: (String) -> Unit
) {
    when (charactersUiState) {
        is CharactersUiState.Error -> {
            ErrorMassage(textFont) {
                viewModel.getCharacters()
            }
        }

        CharactersUiState.Loading -> {
            RoundLoad()
        }

        is CharactersUiState.Success -> {
            CharactersScreen(
                charactersUiState.characters,
                textFont,
                context,
                viewModel,
                scrollState,
                onClick = onClick
            )
        }
    }
}
