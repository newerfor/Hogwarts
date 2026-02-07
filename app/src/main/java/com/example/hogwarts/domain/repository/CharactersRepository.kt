package com.example.hogwarts.domain.repository

import com.example.hogwarts.domain.model.CharacterDomainModel
import com.example.hogwarts.domain.model.CharacterFullInfoDomainModel

interface CharactersRepository {
    suspend fun getCharacters(isFirstLaunch: Boolean,
                              house: String?,
                              isStaff: Boolean?,
                              isStudent: Boolean?,
                              isWizard: Boolean?,
                              ancestry: String?,
                              nameQuery: String?,
                              offset: Int): List<CharacterDomainModel>
    suspend fun getCharacterById(id:String): CharacterFullInfoDomainModel
}