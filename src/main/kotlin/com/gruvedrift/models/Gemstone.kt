package com.gruvedrift.models

import java.util.concurrent.atomic.AtomicInteger

// Factory Method design pattern
class Gemstone
    private constructor( val id: Int, var name: String, var birthMonth: MONTH) {

    companion object{
        private val idCounter =  AtomicInteger()
        fun newGemstone( name: String, birthMonth: MONTH ) = Gemstone(idCounter.getAndIncrement(), name, birthMonth)
    }
}

enum class MONTH {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

val gemStones = mutableListOf(
    Gemstone.newGemstone("Garnet", MONTH.JANUARY),
    Gemstone.newGemstone("Amethyst", MONTH.FEBRUARY),
    Gemstone.newGemstone("Aquamarine", MONTH.MARCH),
    Gemstone.newGemstone("Diamond", MONTH.APRIL),
    Gemstone.newGemstone("Emerald", MONTH.MAY),
    Gemstone.newGemstone("Alexandrite", MONTH.JUNE),
    Gemstone.newGemstone("Ruby", MONTH.JULY),
    Gemstone.newGemstone("Spinel", MONTH.AUGUST),
    Gemstone.newGemstone("Sapphire", MONTH.SEPTEMBER),
    Gemstone.newGemstone("Tourmaline", MONTH.OCTOBER),
    Gemstone.newGemstone("Golden Topaz", MONTH.NOVEMBER),
    Gemstone.newGemstone("Blue Topaz", MONTH.DECEMBER),
)

