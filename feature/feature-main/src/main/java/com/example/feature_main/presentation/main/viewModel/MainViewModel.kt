package com.example.feature_main.presentation.main.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_domain.model.CharacterDomainModel
import com.example.core_domain.useCase.GetCharactersUseCase
import com.example.feature_main.presentation.main.constant.MainLogicConstant.OFFSET_CALCULATE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _charactersUiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val charactersUiState: StateFlow<CharactersUiState> = _charactersUiState.asStateFlow()
    private val _characters = MutableStateFlow<List<CharacterDomainModel>>(emptyList())
    val characters: StateFlow<List<CharacterDomainModel>> = _characters.asStateFlow()
    private var isFirstLaunch = mutableStateOf(true)
    private var filer_house: String? = null
    private var filer_isStaff: Boolean? = null
    private var filer_isStudent: Boolean? = null
    private var filer_isWizard: Boolean? = null
    private var filer_ancestry: String? = null
    private var filer_nameQuery: String? = null
    private var offset = mutableStateOf(0)

    init {
        Log.d("laekghnkjehghejkbgehjkg", "INIt: dfhdfg")
        getCharacters()
    }

    fun getCharacters() {
        Log.d("laekghnkjehghejkbgehjkg", "saveCharacter: dfhdfg")
        viewModelScope.launch {
            try {
                if (isFirstLaunch.value) {
                    _charactersUiState.value = CharactersUiState.Loading
                }
                val result = getCharactersUseCase(
                    isFirstLaunch = isFirstLaunch.value,
                    house = filer_house,
                    isStaff = filer_isStaff,
                    isStudent = filer_isStudent,
                    isWizard = filer_isWizard,
                    ancestry = filer_ancestry,
                    nameQuery = filer_nameQuery,
                    offset = offset.value
                )
                result.onSuccess { domainCharacters ->
                    _characters.value = _characters.value + domainCharacters
                    _charactersUiState.value = CharactersUiState.Success(characters.value)
                    offset.value += OFFSET_CALCULATE
                    isFirstLaunch.value = false
                }.onFailure { error ->
                    _charactersUiState.value =
                        CharactersUiState.Error(error.message ?: "Unknown error")
                    _characters.value = emptyList()
                }
            } catch (e: Exception) {
                _charactersUiState.value = CharactersUiState.Error(e.message ?: "Unknown error")
                _characters.value = emptyList()
            }
        }
    }

    fun getCharactersAsFilters(
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
    ) {
        Log.d("laekghnkjehghejkbgehjkg", "Filters: dfhdfg")
        filer_house = house
        filer_isStaff = isStaff
        filer_isStudent = isStudent
        filer_isWizard = isWizard
        filer_ancestry = ancestry
        filer_nameQuery = nameQuery
        offset.value = 0
        _characters.value = emptyList()
        getCharacters()
    }
}