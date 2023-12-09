package com.gruvedrift

import com.gruvedrift.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>):Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureTemplating()
}
