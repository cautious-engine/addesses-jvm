package com.cautiousengine.addresses.ktor.plugins

import com.cautiousengine.addresses.ktor.routes.addressRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        addressRouting()
    }
}
