package com.carlosquezada.recetas.application

import android.app.Application
import com.carlosquezada.core.di.networkModule
import com.carlosquezada.recipe_dashboard.data.di.dashBoardModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RecipeApplication)
            modules(networkModule, dashBoardModule)
        }
    }

}