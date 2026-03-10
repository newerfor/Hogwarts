package com.example.feature_main.presentation.main.di

import com.example.feature_main.presentation.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {  // ✅ просто val
    viewModel { MainViewModel(getCharactersUseCase = get()) }
}