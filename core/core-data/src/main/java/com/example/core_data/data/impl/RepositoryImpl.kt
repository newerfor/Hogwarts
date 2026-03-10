package com.example.core_data.data.impl


import com.example.core_data.data.constant.DataBaseConstant.LIMIT_DATA_BASE
import com.example.core_data.data.local.localRepository.LocalDataSource
import com.example.core_data.data.mapper.Mapper
import com.example.core_data.data.remote.model.CharactersModel
import com.example.core_data.data.remote.remoteRepository.RemoteDataSource
import com.example.core_domain.model.CharacterDomainModel
import com.example.core_domain.model.CharacterFullInfoDomainModel
import com.example.core_domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
) : CharactersRepository {
    override suspend fun getCharacters(
        isFirstLaunch: Boolean,
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        offset: Int
    ): List<CharacterDomainModel> {
        return if (isFirstLaunch) {
            val apiResult = remoteDataSource.getCharactersRemote()
            apiResult.take(LIMIT_DATA_BASE).forEach { character ->
                localDataSource.saveCharacter(character)
            }
            saveCharactersBatch(apiResult.drop(LIMIT_DATA_BASE))
            localDataSource.getCharacters(
                house = house,
                isStaff = isStaff,
                isStudent = isStudent,
                isWizard = isWizard,
                ancestry = ancestry,
                nameQuery = nameQuery,
                offset = offset
            ).map { mapper.mapEntityToDomain(it) }
        } else {
            localDataSource.getCharacters(
                house = house,
                isStaff = isStaff,
                isStudent = isStudent,
                isWizard = isWizard,
                ancestry = ancestry,
                nameQuery = nameQuery,
                offset = offset
            ).map { mapper.mapEntityToDomain(it) }
        }
    }

    fun saveCharactersBatch(characters: List<CharactersModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            characters.forEach { character ->
                localDataSource.saveCharacter(character)
            }
        }
    }

    override suspend fun getCharacterById(id: String): CharacterFullInfoDomainModel {
        return mapper.mapEntityToDomainFullInfo(localDataSource.getCharacterById(id))
    }

}