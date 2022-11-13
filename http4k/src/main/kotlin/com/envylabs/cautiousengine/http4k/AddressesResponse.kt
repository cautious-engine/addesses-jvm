package com.envylabs.cautiousengine.http4k

import java.time.Instant

data class AddressesResponse(
    val addresses: List<Address> = emptyList()
) {
    val results: Int = addresses.size

    data class Address(
        val street: String,
        val city: String,
        val state: String,
        val zipCode: String,
        val createdAt: Instant
    )
}
