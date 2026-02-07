package com.example.hogwarts.data.impl


import com.example.hogwarts.data.constant.DataBaseConstant.LIMIT_DATA_BASE
import com.example.hogwarts.data.local.LocalRepository.LocalDataSource
import com.example.hogwarts.data.mapper.Mapper
import com.example.hogwarts.data.remote.model.CharactersModel
import com.example.hogwarts.data.remote.remoteRepository.RemoteDataSource
import com.example.hogwarts.domain.model.CharacterDomainModel
import com.example.hogwarts.domain.model.CharacterFullInfoDomainModel
import com.example.hogwarts.domain.repository.CharactersRepository
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
            apiResult.take(LIMIT_DATA_BASE).forEach {
                character -> localDataSource.saveCharacter(character)
            }
            saveCharactersBatch(apiResult.drop(LIMIT_DATA_BASE))
            localDataSource.getCharacters(house = house,
                isStaff = isStaff,
                isStudent = isStudent,
                isWizard = isWizard,
                ancestry = ancestry,
                nameQuery = nameQuery,
                offset = offset).map { mapper.mapEntityToDomain(it) }
        } else {
            localDataSource.getCharacters(house = house,
                isStaff = isStaff,
                isStudent = isStudent,
                isWizard = isWizard,
                ancestry = ancestry,
                nameQuery = nameQuery,
                offset = offset).map { mapper.mapEntityToDomain(it) }
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