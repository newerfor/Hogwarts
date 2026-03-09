package com.example.core_data.data.local.localRepository

import com.example.core_data.data.local.entity.CharactersEntityModel
import com.example.core_data.data.remote.model.CharactersModel

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