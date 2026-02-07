package com.example.hogwarts.domain.useCase

import com.example.hogwarts.domain.model.CharacterDomainModel
import com.example.hogwarts.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(
        isFirstLaunch: Boolean,
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        offset: Int
    ): Result<List<CharacterDomainModel>> = runCatching {
        repository.getCharacters(
            house = house,
            isStaff = isStaff,
            isStudent = isStudent,
            isWizard = isWizard,
            ancestry = ancestry,
            nameQuery = nameQuery,
            offset = offset,
            isFirstLaunch = isFirstLaunch
        )
    }
}