package org.example.jetshop.remote

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient
