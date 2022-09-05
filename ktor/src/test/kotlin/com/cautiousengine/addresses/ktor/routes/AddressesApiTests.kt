package com.cautiousengine.addresses.ktor.routes

import com.cautiousengine.addresses.ktor.models.AddressesResponse
import com.cautiousengine.addresses.ktor.plugins.ErrorResponse
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.jackson.jackson
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication
import org.junit.Test
import kotlin.test.assertEquals

internal class AddressesApiTests {

    @Test
    fun `returns 5 addresses by default`() = testApplication {
        val client = httpTestClient()

        val response = client.get("/addresses")
        val responseBody: AddressesResponse = response.body()

        assertEquals(200, response.status.value)
        assertEquals(5, responseBody.results)
        assertEquals(5, responseBody.addresses.size)
    }

    @Test
    fun `accepts 'min-results' and 'max-results' query parameters`() = testApplication {
        val client = httpTestClient()

        val response = client.get("/addresses?min-results=8&max-results=8")
        val responseBody: AddressesResponse = response.body()

        assertEquals(200, response.status.value)
        assertEquals(8, responseBody.results)
        assertEquals(8, responseBody.addresses.size)
    }

    @Test
    fun `throws 422 error for invalid 'min-results' query parameter value`() = testApplication {
        val client = httpTestClient()

        val response = client.get("/addresses?min-results=zero")
        val responseBody: ErrorResponse = response.body()

        println(response.bodyAsText())
        assertEquals(422, response.status.value)
        assertEquals(422, responseBody.code)
        assertEquals("For input string: \"zero\"", responseBody.message)
    }

    private fun ApplicationTestBuilder.httpTestClient() = createClient {
        install(ContentNegotiation) {
            jackson {
                propertyNamingStrategy = PropertyNamingStrategies.SnakeCaseStrategy()
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                registerModule(JavaTimeModule())
            }
        }
    }
}
