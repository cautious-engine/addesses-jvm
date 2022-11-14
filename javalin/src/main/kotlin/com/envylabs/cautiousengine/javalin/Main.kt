package com.envylabs.cautiousengine.javalin

import com.cautiousengine.address.generator.faker.FakerRandomAddressGenerator
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.json.JavalinJackson

fun main() {
    val app = Javalin.create {
        it.jsonMapper(JavalinJackson(JacksonConfig.defaultObjectMapper()))
    }.start(8080)

    val randomAddressGenerator = FakerRandomAddressGenerator()
    val addressesHandler = AddressesHandler(randomAddressGenerator = randomAddressGenerator)

    app.routes {
        get("/addresses") {
            it.json(addressesHandler.getAll())
        }
    }
}
