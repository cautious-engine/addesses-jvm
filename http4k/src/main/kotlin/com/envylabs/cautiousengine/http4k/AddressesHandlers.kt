package com.envylabs.cautiousengine.http4k

import com.cautiousengine.address.RandomAddressGenerator
import com.envylabs.cautiousengine.http4k.JacksonConfig.auto
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status

class AddressesHandlers(
    private val randomAddressGenerator: RandomAddressGenerator
) {

    fun index() = { _: Request ->
        val addressesLens = Body.auto<AddressesResponse>().toLens()
        val transformedAddresses = randomAddressGenerator
            .generate()
            .map {
                AddressesResponse.Address(
                    street = it.street,
                    city = it.city,
                    state = it.state,
                    zipCode = it.zipCode,
                    createdAt = it.createdAt
                )
            }

        addressesLens(AddressesResponse(addresses = transformedAddresses), Response(Status.OK))
    }
}
