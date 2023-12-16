package com.gruvedrift.plugins

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.server.application.*
import io.ktor.server.freemarker.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        /* Configure location from where the templates will be loaded from */
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "/templates")
        /* Escape <tags> in order to mitigate XSS injection attacks: E.g <h1>Hello</h1> is printed as <h1>Hello</1> and not Hello */
        outputFormat = HTMLOutputFormat.INSTANCE

    }
}
