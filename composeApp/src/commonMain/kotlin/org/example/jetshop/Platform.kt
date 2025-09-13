package org.example.jetshop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform