package com.envylabs.cautiousengine.http4k

import com.cautiousengine.address.generator.faker.FakerRandomAddressGenerator
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

val undertow = Undertow(8080)
val randomAddressGenerator = FakerRandomAddressGenerator()
val addressesHandlers = AddressesHandlers(randomAddressGenerator = randomAddressGenerator)

fun main() {
    App().handlers().asServer(undertow).start()
}

class App {
    fun handlers(): HttpHandler {
        return routes(
            "/addresses" bind routes(
                "/" bind Method.GET to addressesHandlers.index()
            )
        )
    }
}
