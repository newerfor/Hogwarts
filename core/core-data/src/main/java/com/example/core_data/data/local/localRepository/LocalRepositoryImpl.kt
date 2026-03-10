package com.example.core_data.data.local.localRepository

import android.content.Context
import com.example.core_data.data.local.dao.HogwartsCharactersDao
import com.example.core_data.data.local.entity.CharactersEntityModel
import com.example.core_data.data.local.localMapper.LocalMapper
import com.example.core_data.data.local.localRepository.SaveImageHelper.saveImageFromUrl
import com.example.core_data.data.remote.model.CharactersModel

class LocalRepositoryImpl(
    private val dao: HogwartsCharactersDao,
    private val mapper: LocalMapper,
    private val context: Context
) : LocalDataSource {
    override suspend fun getCharacters(
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        offset: Int
    ): List<CharactersEntityModel> {
        return dao.getCharacters(
            house = house,
            isStaff = isStaff,
            isStudent = isStudent,
            isWizard = isWizard,
            ancestry = ancestry,
            nameQuery = nameQuery,
            offset = offset
        )
    }

    override suspend fun getCharacterById(id: String): CharactersEntityModel {
        return dao.getCharacterById(id)
    }

    override suspend fun saveCharacter(character: CharactersModel) {
        if (character.image == null) {
            dao.saveCharacter(mapper.mapRemoteToLocal(character))
        } else {
            val savedPath = saveImageFromUrl(
                context = context,
                imageUrl = character.image,
            )
            val characterWithLocalImage = character.copy(
                image = savedPath
            )
            dao.saveCharacter(mapper.mapRemoteToLocal(characterWithLocalImage))
        }
    }
}