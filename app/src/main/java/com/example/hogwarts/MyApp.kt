package com.example.hogwarts

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import com.example.core_data.data.di.dataModule
import com.example.core_domain.di.domainModule
import com.example.feature_character_detail.di.detailModule
import com.example.feature_main.presentation.main.di.mainModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                dataModule,
                domainModule,
                mainModule,
                detailModule
            )
        }
    }
}
