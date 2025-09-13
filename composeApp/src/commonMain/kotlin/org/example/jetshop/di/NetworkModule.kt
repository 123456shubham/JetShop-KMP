package org.example.jetshop.di

import io.ktor.client.HttpClient
import org.example.jetshop.remote.httpClient
import org.koin.dsl.module


fun networkModule()= module{
    single<HttpClient> {
        httpClient
    }
}