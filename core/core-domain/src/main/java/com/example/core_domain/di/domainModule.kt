package com.example.core_domain.di

import com.example.core_domain.useCase.GetCharacterByIdUseCase
import com.example.core_domain.useCase.GetCharactersUseCase
import org.koin.dsl.module

val domainModule = module {  // ✅ просто val
    factory { GetCharactersUseCase(repository = get()) }
    factory { GetCharacterByIdUseCase(repository = get()) }
}