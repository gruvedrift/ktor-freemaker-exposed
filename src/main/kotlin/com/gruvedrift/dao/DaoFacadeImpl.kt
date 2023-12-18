package com.gruvedrift.dao

import com.gruvedrift.dao.DatabaseSingleton.dbQuery
import com.gruvedrift.models.Gemstone
import com.gruvedrift.models.Gemstones
import com.gruvedrift.models.MONTH
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DaoFacadeImpl : DaoFacade {

    private fun resultRowToGemstone(row: ResultRow) = Gemstone(
        id = row[Gemstones.id],
        name = row[Gemstones.name],
        birthMonth = MONTH.valueOf(row[Gemstones.birthMonth]),
    )

    override suspend fun allGemstones(): List<Gemstone> = dbQuery {
        Gemstones.selectAll().map{ resultRowToGemstone(it) }
    }

    override suspend fun gemstone(id: Int): Gemstone?  = dbQuery {
       Gemstones.select { Gemstones.id eq id }
           .map { resultRowToGemstone(it) }
           .singleOrNull()
    }

    override suspend fun addNewGemstone(name: String, birthMonth: MONTH): Gemstone? = dbQuery {
       Gemstones.insert {
            it[Gemstones.name] = name
            it[Gemstones.birthMonth] = birthMonth.toString()
        }.resultedValues?.singleOrNull()?.let { resultRowToGemstone(it) }
    }

    override suspend fun editGemstone(id: Int, name: String, birthMonth: MONTH): Boolean = dbQuery {
        Gemstones.update({ Gemstones.id eq id }) {
            it[Gemstones.name] = name
            it[Gemstones.birthMonth] = birthMonth.toString()
        } > 0
    }

    override suspend fun deleteGemstone(id: Int) : Boolean = dbQuery {
        Gemstones.deleteWhere { Gemstones.id eq id }  > 0
    }
}

val dao: DaoFacade = DaoFacadeImpl().apply {
    runBlocking {
         if (allGemstones().isEmpty()) {
            addNewGemstone(
                name = "Celestite",
                birthMonth = MONTH.DECEMBER
            )
        }
    }
}
