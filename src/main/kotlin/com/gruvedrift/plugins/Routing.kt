package com.gruvedrift.plugins

import com.gruvedrift.dao.dao

import com.gruvedrift.models.MONTH
import com.gruvedrift.models.monthList
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        /* File route */
        staticResources("/static", "files")
        get("/") {
            /* Redirect to default */
            call.respondRedirect("gemstones")
        }
        route("gemstones") {

            /* Home page */
            get{
                call.respond(FreeMarkerContent("index.ftl", mapOf("gemstones" to dao.allGemstones())))
            }

            /* Get gemstone by id */
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("gem.ftl", mapOf("gem" to dao.gemstone(id))))
            }

            /* Edit existing gemstone */
            get("{id}/edit") {
                val id = call.parameters.getOrFail("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("gem" to dao.gemstone(id), "monthList" to monthList)))
            }

            get("new") {
                call.respond(FreeMarkerContent("new.ftl", mapOf("monthList" to monthList)))
            }

            /* Create new gemstone entry */
            post {
                val formParameters = call.receiveParameters()
                val entry = dao.addNewGemstone(
                    formParameters.getOrFail("gemstoneName"),
                    MONTH.valueOf(formParameters.getOrFail("month"))
                )
                call.respondRedirect("/gemstones/${entry?.id}")
            }

            /* Update or delete gemstone */
            post ("{id}"){
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when(formParameters.getOrFail("_action")) {
                    "update" -> {
                        dao.editGemstone(
                            id,
                            formParameters.getOrFail("name"),
                            MONTH.valueOf(formParameters.getOrFail("month")),
                        )
                        call.respondRedirect("/gemstones/$id")
                    }
                    "delete" -> {
                        dao.deleteGemstone(id)
                        call.respondRedirect("/gemstones")
                    }
                }
            }
        }
    }
}
