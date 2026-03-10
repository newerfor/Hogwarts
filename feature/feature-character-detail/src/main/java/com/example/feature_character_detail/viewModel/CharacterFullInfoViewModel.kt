package com.example.feature_character_detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_domain.useCase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterFullInfoViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {
    private val _characterByIdState =
        MutableStateFlow<CharacterByIdUiState>(CharacterByIdUiState.Loading)
    val characterByIdState: StateFlow<CharacterByIdUiState> = _characterByIdState.asStateFlow()
    fun getCharacterById(id: String) {
        viewModelScope.launch {
            _characterByIdState.value = CharacterByIdUiState.Loading
            try {
                val result = getCharacterByIdUseCase(id)
                result.onSuccess { domainCharacter ->
                    _characterByIdState.value = CharacterByIdUiState.Success(domainCharacter)
                }.onFailure { error ->
                    _characterByIdState.value = CharacterByIdUiState.Error(error.toString())
                }
            } catch (e: Exception) {
                _characterByIdState.value = CharacterByIdUiState.Error(e.toString())
            }
        }
    }
}