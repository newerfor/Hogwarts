package com.example.hogwarts.di

import com.example.hogwarts.data.impl.RepositoryImpl
import com.example.hogwarts.data.local.LocalRepository.LocalDataSource
import com.example.hogwarts.data.local.LocalRepository.LocalRepositoryImpl
import com.example.hogwarts.data.local.dao.HogwartsCharactersDao
import com.example.hogwarts.data.local.database.DataBaseProvider
import com.example.hogwarts.data.local.database.HogwartsDatabase
import com.example.hogwarts.data.local.localMapper.LocalMapper
import com.example.hogwarts.data.mapper.Mapper
import com.example.hogwarts.data.remote.api.ApiClient
import com.example.hogwarts.data.remote.api.ApiService
import com.example.hogwarts.data.remote.remoteRepository.RemoteDataSource
import com.example.hogwarts.data.remote.remoteRepository.RemoteRepositoryImpl
import com.example.hogwarts.domain.repository.CharactersRepository
import com.example.hogwarts.domain.useCase.GetCharacterByIdUseCase
import com.example.hogwarts.domain.useCase.GetCharactersUseCase
import com.example.hogwarts.presentation.CharacterFullInfo.viewModel.CharacterFullInfoViewModel
import com.example.hogwarts.presentation.main.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


object AppModule {
    val module = module {
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
        single<CharactersRepository> {
            RepositoryImpl(
                localDataSource = get(),
                remoteDataSource = get(),
                mapper = get()
            )
        }
        factory { GetCharactersUseCase(repository = get()) }
        factory { GetCharacterByIdUseCase(repository = get()) }
        viewModel { MainViewModel(getCharactersUseCase = get()) }
        viewModel { CharacterFullInfoViewModel(getCharacterByIdUseCase = get())
        }
    }
}