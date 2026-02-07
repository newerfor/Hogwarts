package com.example.hogwarts.data.local.LocalRepository

import com.example.hogwarts.data.local.entity.CharactersEntityModel
import com.example.hogwarts.data.remote.model.CharactersModel

interface LocalDataSource {
    suspend fun getCharacters(
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        offset: Int
    ): List<CharactersEntityModel>

    suspend fun getCharacterById(id: String): CharactersEntityModel
    suspend fun saveCharacter(character: CharactersModel)
}