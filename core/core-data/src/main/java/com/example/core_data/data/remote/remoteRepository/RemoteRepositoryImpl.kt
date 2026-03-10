package com.example.core_data.data.remote.remoteRepository

import com.example.core_data.data.remote.api.ApiService
import com.example.core_data.data.remote.model.CharactersModel

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getCharactersRemote(): List<CharactersModel> {
        return apiService.getCharacters()
    }
}