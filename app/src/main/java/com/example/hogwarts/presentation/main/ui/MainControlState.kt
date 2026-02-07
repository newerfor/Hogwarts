package com.example.hogwarts.presentation.main.ui

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import com.example.hogwarts.presentation.main.viewModel.CharactersUiState
import com.example.hogwarts.presentation.main.viewModel.MainViewModel
import com.example.hogwarts.presentation.sharedUi.ui.StateHelper.ErrorMassage
import com.example.hogwarts.presentation.sharedUi.ui.StateHelper.RoundLoad
import com.example.hogwarts.presentation.sharedUi.ui.TextFont

class MainControlState {
    @Composable
    fun ControlState(
        charactersUiState: CharactersUiState,
        textFont: TextFont,
        context: Context,
        viewModel: MainViewModel,
        scrollState: ScrollState
    ) {
        when(charactersUiState){
            is CharactersUiState.Error -> {
                ErrorMassage(textFont){
                    viewModel.getCharacters()
                }
            }
            CharactersUiState.Loading -> {
                RoundLoad()
            }
            is CharactersUiState.Success -> {
                CharactersSpace().CharactersScreen(charactersUiState.characters,textFont,context,viewModel,scrollState)
            }
        }
    }
}