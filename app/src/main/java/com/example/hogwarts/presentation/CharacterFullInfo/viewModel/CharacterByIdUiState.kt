package com.example.hogwarts.presentation.CharacterFullInfo.viewModel

import com.example.hogwarts.domain.model.CharacterFullInfoDomainModel

sealed interface CharacterByIdUiState {
    data object Loading : CharacterByIdUiState
    data class Success(val character: CharacterFullInfoDomainModel) : CharacterByIdUiState
    data class Error(val error: String) : CharacterByIdUiState
}
