package com.example.hogwarts.domain.useCase

import com.example.hogwarts.domain.model.CharacterDomainModel
import com.example.hogwarts.domain.model.CharacterFullInfoDomainModel
import com.example.hogwarts.domain.repository.CharactersRepository
import com.example.hogwarts.presentation.CharacterFullInfo.ui.CharacterFullInfo

class GetCharacterByIdUseCase (
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(id:String): Result<CharacterFullInfoDomainModel> = runCatching {
        repository.getCharacterById(id)
    }
}