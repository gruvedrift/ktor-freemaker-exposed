package com.gruvedrift.models

import org.jetbrains.exposed.sql.*
import java.util.concurrent.atomic.AtomicInteger

// Factory Method design pattern
data class Gemstone (
    val id: Int,
    val name: String,
    val birthMonth: MONTH,
) {
    companion object{
        private val idCounter =  AtomicInteger()
        fun newGemstone( name: String, birthMonth: MONTH ) = Gemstone(idCounter.getAndIncrement(), name, birthMonth)
    }

}

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

//val gemstones = mutableListOf(
//    Gemstone.newGemstone("Garnet", MONTH.JANUARY),
//    Gemstone.newGemstone("Amethyst", MONTH.FEBRUARY),
//    Gemstone.newGemstone("Aquamarine", MONTH.MARCH),
//    Gemstone.newGemstone("Diamond", MONTH.APRIL),
//    Gemstone.newGemstone("Emerald", MONTH.MAY),
//    Gemstone.newGemstone("Alexandrite", MONTH.JUNE),
//    Gemstone.newGemstone("Ruby", MONTH.JULY),
//    Gemstone.newGemstone("Spinel", MONTH.AUGUST),
//    Gemstone.newGemstone("Sapphire", MONTH.SEPTEMBER),
//    Gemstone.newGemstone("Tourmaline", MONTH.OCTOBER),
//    Gemstone.newGemstone("Golden Topaz", MONTH.NOVEMBER),
//    Gemstone.newGemstone("Blue Topaz", MONTH.DECEMBER),
//)
//
