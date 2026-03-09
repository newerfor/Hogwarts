package com.example.core_data.data.remote.remoteRepository

import com.example.core_data.data.remote.model.CharactersModel

interface RemoteDataSource {
    suspend fun getCharactersRemote(): List<CharactersModel>
}