package com.example.feature_character_detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.feature_character_detail.viewModel.CharacterFullInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(
    id: String,
    onBackClick: () -> Unit
) {
    CharacterFullInfoView(id = id, onBackClick = onBackClick)
}

@Composable
fun CharacterFullInfoView(
    characterViewModel: CharacterFullInfoViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    id: String,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        characterViewModel.getCharacterById(id)
    }
    val characterByIdState by characterViewModel.characterByIdState.collectAsState()
    ControlState(characterByIdState, textFont, characterViewModel, id, onBackClick)
}