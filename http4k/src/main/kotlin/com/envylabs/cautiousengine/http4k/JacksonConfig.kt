package com.envylabs.cautiousengine.http4k

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.http4k.format.ConfigurableJackson

// To leverage this custom object mapper, this `JacksonConfig.auto` must be imported in place of
// `org.http4k.format.Jackson.auto` everywhere lensing is used.
object JacksonConfig : ConfigurableJackson(
    ObjectMapper().apply {
        this.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        this.registerModule(JavaTimeModule()) // for JSR310
        this.registerModule(kotlinModule())
    }
)

private fun kotlinModule() = KotlinModule.Builder()
    .configure(KotlinFeature.NullToEmptyCollection, true)
    .configure(KotlinFeature.NullToEmptyMap, true)
    .configure(KotlinFeature.NullIsSameAsDefault, true)
    .build()