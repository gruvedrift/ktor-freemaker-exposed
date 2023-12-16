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
            get{
                println("Gemstones called")
                call.respond(FreeMarkerContent("index.ftl", mapOf("gemstones" to gemstones)))
            }
            get("{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                call.respond(FreeMarkerContent("gem.ftl", mapOf("gem" to gemstones.find { it.id == id })))
            }
            get("{id}/edit") {
                // show page with fields to edit gemstone
            }
            get("new") {
                // Page with form for new Gemstone
                call.respond(FreeMarkerContent("new.ftl", mapOf("monthList" to monthList)))
            }
            post {
                val formParameters = call.receiveParameters()
                val gemstoneName = formParameters.getOrFail("gemstoneName")
                val month = formParameters.getOrFail("month")
                val newEntry = Gemstone.newGemstone(
                    gemstoneName,
                    MONTH.valueOf(month)
                )
                gemstones.add(newEntry)
                call.respondRedirect("/gemstones/${newEntry.id}")
            }
            post ("{id}"){
                // update or delete gemstone
            }
        }
    }
}
