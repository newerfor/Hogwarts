package com.example.hogwarts.data.remote.remoteRepository

import com.example.hogwarts.data.remote.model.CharactersModel

interface RemoteDataSource {
    suspend fun getCharactersRemote(): List<CharactersModel>
}