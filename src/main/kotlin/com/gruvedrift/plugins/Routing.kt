package com.gruvedrift.plugins

import com.gruvedrift.models.Gemstone
import com.gruvedrift.models.MONTH
import com.gruvedrift.models.gemstones
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
        staticResources("/static", "files")
        get("/") {
            /* Redirect to default */
            call.respondRedirect("gemstones")
        }
        route("gemstones") {
            /* Home page */
            get{
                call.respond(FreeMarkerContent("index.ftl", mapOf("gemstones" to gemstones)))
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("gem.ftl", mapOf("gem" to gemstones.find { it.id == id })))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("gem" to gemstones.find { it.id == id }, "monthList" to monthList)))
            }
            get("new") {
                call.respond(FreeMarkerContent("new.ftl", mapOf("monthList" to monthList)))
            }
            /* Create new gemstone entry */
            post {
                val formParameters = call.receiveParameters()
                val newEntry = Gemstone.newGemstone(
                    formParameters.getOrFail("gemstoneName"),
                    MONTH.valueOf(formParameters.getOrFail("month"))
                )
                gemstones.add(newEntry)
                call.respondRedirect("/gemstones/${newEntry.id}")
            }
            /* Update or delete gemstone */
            post ("{id}"){
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when(formParameters.getOrFail("_action")) {
                    "update" -> {
                        val index = gemstones.indexOf(gemstones.find { it.id == id })
                        gemstones[index].name = formParameters.getOrFail("name")
                        gemstones[index].birthMonth = MONTH.valueOf(formParameters.getOrFail("month"))
                        call.respondRedirect("/gemstones/$id")
                    }
                    "delete" -> {
                        gemstones.removeIf { it.id == id }
                        call.respondRedirect("/gemstones")

                    }
                }
            }
        }
    }
}
