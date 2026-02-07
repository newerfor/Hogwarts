package com.example.hogwarts.presentation.main.viewModel

import com.example.hogwarts.domain.model.CharacterDomainModel

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data class Success(val characters: List<CharacterDomainModel>) : CharactersUiState
    data class Error(val error: String) : CharactersUiState
}
