package org.example.jetshop


import org.example.jetshop.db.DatabaseFactory
import org.example.jetshop.di.initKoin
import org.example.jetshop.preference.MultiplatformSettingsFactory
import org.koin.dsl.module

val iosModules = module {
    single { DatabaseFactory() }
    single { MultiplatformSettingsFactory() }
}

fun initKoinIOS() = initKoin(additionalModules = listOf(iosModules))