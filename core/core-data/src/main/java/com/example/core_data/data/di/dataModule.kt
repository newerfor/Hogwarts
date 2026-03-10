package com.example.core_data.data.di

import com.example.core_data.data.impl.RepositoryImpl
import org.koin.dsl.module
import com.example.core_data.data.local.dao.HogwartsCharactersDao
import com.example.core_data.data.local.database.DataBaseProvider
import com.example.core_data.data.local.database.HogwartsDatabase
import com.example.core_data.data.local.localMapper.LocalMapper
import com.example.core_data.data.local.localRepository.LocalDataSource
import com.example.core_data.data.local.localRepository.LocalRepositoryImpl
import com.example.core_data.data.mapper.Mapper
import com.example.core_data.data.remote.api.ApiClient
import com.example.core_data.data.remote.api.ApiService
import com.example.core_data.data.remote.remoteRepository.RemoteDataSource
import com.example.core_data.data.remote.remoteRepository.RemoteRepositoryImpl
import com.example.core_domain.repository.CharactersRepository
import org.koin.android.ext.koin.androidContext

val dataModule = module {  // ✅ просто val на верхнем уровне
    single<CharactersRepository> {
        RepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get(),
            mapper = get()
        )
    }
    single<HogwartsDatabase> {
        DataBaseProvider.getDatabase(androidContext())
    }
    single<HogwartsCharactersDao> {
        get<HogwartsDatabase>().hogwartsCharactersDao()
    }
    single { LocalMapper() }
    single { Mapper() }
    single<ApiService> { ApiClient.instance }
    single<LocalDataSource> {
        LocalRepositoryImpl(
            dao = get(),
            mapper = get(),
            androidContext()
        )
    }
    single<RemoteDataSource> {
        RemoteRepositoryImpl(
            apiService = get()
        )
    }
}