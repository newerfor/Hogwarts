package com.example.core_domain.repository

import com.example.core_domain.model.CharacterDomainModel
import com.example.core_domain.model.CharacterFullInfoDomainModel

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