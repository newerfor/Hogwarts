package com.example.hogwarts.presentation.CharacterFullInfo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import com.example.hogwarts.presentation.CharacterFullInfo.viewModel.CharacterByIdUiState
import com.example.hogwarts.presentation.CharacterFullInfo.viewModel.CharacterFullInfoViewModel
import com.example.hogwarts.presentation.sharedUi.ui.StateHelper.ErrorMassage
import com.example.hogwarts.presentation.sharedUi.ui.StateHelper.RoundLoad
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import com.example.hogwarts.R
class ChildFullInfoControlState {
    @Composable
    fun ControlState(characterByIdState: CharacterByIdUiState, textFont: TextFont,viewModel: CharacterFullInfoViewModel,id:String) {
        when (characterByIdState) {
            is CharacterByIdUiState.Error -> {
                ErrorMassage(textFont){
                    viewModel.getCharacterById(id)
                }
            }
            CharacterByIdUiState.Loading -> {
                RoundLoad()
            }
            is CharacterByIdUiState.Success -> {
                Box {
                    Image(painter = SelectedBackGround().getBackGround(characterByIdState.character.house?: stringResource(R.string.data_not_found)), contentDescription = "",
                        Modifier.fillMaxSize(), contentScale = Crop)
                    CharacterFullInfoScreen().CharacterFullInfo(characterByIdState.character, textFont)
                }
            }
        }

    }
}