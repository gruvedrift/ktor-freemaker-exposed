package com.gruvedrift.dao

import com.gruvedrift.models.Gemstone
import com.gruvedrift.models.MONTH

/* Interface for persistence logic */

interface DaoFacade {
    suspend fun allGemstones(): List<Gemstone>
    suspend fun gemstone(id: Int): Gemstone?
    suspend fun addNewGemstone(name: String, birthMonth: MONTH): Gemstone?
    suspend fun editGemstone(id: Int, name: String, birthMonth: MONTH): Boolean
    suspend fun deleteGemstone(id: Int): Boolean
}
