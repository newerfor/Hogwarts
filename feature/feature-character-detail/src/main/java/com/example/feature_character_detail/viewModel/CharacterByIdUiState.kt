package com.example.feature_character_detail.viewModel

import com.example.core_domain.model.CharacterFullInfoDomainModel

sealed interface CharacterByIdUiState {
    data object Loading : CharacterByIdUiState
    data class Success(val character: CharacterFullInfoDomainModel) : CharacterByIdUiState
    data class Error(val error: String) : CharacterByIdUiState
}
