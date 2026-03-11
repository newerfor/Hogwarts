package com.example.feature_character_detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import com.example.core_ui.sharedUi.ui.ErrorMassage
import com.example.core_ui.sharedUi.ui.RoundLoad
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.feature_character_detail.R
import com.example.feature_character_detail.viewModel.CharacterByIdUiState
import com.example.feature_character_detail.viewModel.CharacterFullInfoViewModel


@Composable
fun ControlState(
    characterByIdState: CharacterByIdUiState,
    textFont: TextFont,
    viewModel: CharacterFullInfoViewModel,
    id: String,
    onBackClick: () -> Unit,
) {
    when (characterByIdState) {
        is CharacterByIdUiState.Error -> {
            ErrorMassage(textFont) {
                viewModel.getCharacterById(id)
            }
        }

        CharacterByIdUiState.Loading -> {
            RoundLoad()
        }

        is CharacterByIdUiState.Success -> {
            Box {
                Image(
                    painter = getBackGround(
                        characterByIdState.character.house ?: stringResource(
                            R.string.data_not_found
                        )
                    ), contentDescription = "",
                    Modifier.fillMaxSize(), contentScale = Crop
                )
                Column(Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()) {
                    CharacterFullInfo(characterByIdState.character, textFont, onBackClick)
                }
            }
        }
    }

}
