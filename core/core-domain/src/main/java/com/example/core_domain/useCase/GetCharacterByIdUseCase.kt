package com.example.core_domain.useCase

import com.example.core_domain.model.CharacterFullInfoDomainModel
import com.example.core_domain.repository.CharactersRepository

class GetCharacterByIdUseCase(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(id: String): Result<CharacterFullInfoDomainModel> = runCatching {
        repository.getCharacterById(id)
    }
}