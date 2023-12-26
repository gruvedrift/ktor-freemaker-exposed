package com.gruvedrift.models
import org.jetbrains.exposed.sql.*

data class Gemstone (
    val id: Int,
    val name: String,
    val birthMonth: MONTH,
)

object Gemstones : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val birthMonth = varchar("birthMonth", 128)

    override var primaryKey = PrimaryKey(id)
}

enum class MONTH {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

val monthList = MONTH.values().toList()
