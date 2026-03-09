package com.example.core_data.data.remote.api

import com.example.core_data.data.remote.model.CharactersModel
import retrofit2.http.GET
interface ApiService {
    @GET("characters")
    suspend fun getCharacters(): List<CharactersModel>
}