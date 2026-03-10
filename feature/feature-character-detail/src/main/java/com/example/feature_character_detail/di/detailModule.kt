package com.example.feature_character_detail.di

import com.example.feature_character_detail.viewModel.CharacterFullInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { CharacterFullInfoViewModel(getCharacterByIdUseCase = get()) }
}