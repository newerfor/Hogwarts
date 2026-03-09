package com.example.feature_main.presentation.main.viewModel

import com.example.core_domain.model.CharacterDomainModel


sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data class Success(val characters: List<CharacterDomainModel>) : CharactersUiState
    data class Error(val error: String) : CharactersUiState
}
