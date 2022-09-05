package com.cautiousengine.addresses.ktor.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respond(
                HttpStatusCode.UnprocessableEntity,
                ErrorResponse(
                    code = HttpStatusCode.UnprocessableEntity.value,
                    message = cause.message ?: "unable to process request due to invalid input"
                )
            )
        }
        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    code = HttpStatusCode.InternalServerError.value,
                    message = cause.message ?: DEFAULT_ERROR_MESSAGE
                )
            )
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                status,
                ErrorResponse(
                    code = status.value,
                    message = status.description
                )
            )
        }
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String = DEFAULT_ERROR_MESSAGE
)

private const val DEFAULT_ERROR_MESSAGE = "An unknown error has occurred."
