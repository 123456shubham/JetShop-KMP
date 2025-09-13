package org.example.jetshop

import android.app.Application
import org.example.jetshop.db.DatabaseFactory
import org.example.jetshop.di.initKoin
import org.example.jetshop.preference.MultiplatformSettingsFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MainApplication : Application() {
    private val androidModule = module{
        single { DatabaseFactory(this@MainApplication) }
        single { MultiplatformSettingsFactory(this@MainApplication) }
    }
    override fun onCreate() {
        super.onCreate()

    }

    fun setUp(){
        initKoin(
            additionalModules = listOf(androidModule)
        ){
            androidContext(this@MainApplication)
        }

    }
}