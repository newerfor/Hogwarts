package com.example.hogwarts.data.remote.remoteRepository

import android.util.Log
import com.example.hogwarts.data.remote.api.ApiService
import com.example.hogwarts.data.remote.model.CharactersModel

class RemoteRepositoryImpl(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getCharactersRemote(): List<CharactersModel> {
        return  apiService.getCharacters()
    }
}