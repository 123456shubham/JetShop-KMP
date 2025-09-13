package org.example.jetshop.db

import app.cash.sqldelight.db.SqlDriver

actual class DatabaseFactory {
    actual suspend fun createDriver(): SqlDriver {
        TODO("Not yet implemented")
    }
}