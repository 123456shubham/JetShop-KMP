package org.example.jetshop.db

import app.cash.sqldelight.db.SqlDriver

const val DB_FILE_NAME = "JetShop.db"

expect class DatabaseFactory {
    suspend fun createDriver(): SqlDriver
}