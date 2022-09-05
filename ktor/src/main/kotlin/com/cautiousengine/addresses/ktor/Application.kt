package com.cautiousengine.addresses.ktor

import com.cautiousengine.addresses.ktor.plugins.configureRouting
import com.cautiousengine.addresses.ktor.plugins.configureSerialization
import com.cautiousengine.addresses.ktor.plugins.configureStatusPages
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureStatusPages()
}
