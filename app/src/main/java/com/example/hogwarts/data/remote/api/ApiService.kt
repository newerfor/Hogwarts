package com.example.hogwarts.data.remote.api

import androidx.room.Dao
import com.example.hogwarts.data.remote.model.CharactersModel
import retrofit2.http.GET
interface ApiService {
    @GET("characters")
    suspend fun getCharacters(): List<CharactersModel>
}