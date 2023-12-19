package com.gruvedrift.dao

import com.gruvedrift.models.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {

    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./dbdir/db"
        val database = Database.connect(url = jdbcURL, driver = driverClassName)

        transaction(database) {
            SchemaUtils.create(Gemstones)
        }
    }

    /* Util function for all requests. Uses coroutines to perform requests in a non-blocking way. */
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
