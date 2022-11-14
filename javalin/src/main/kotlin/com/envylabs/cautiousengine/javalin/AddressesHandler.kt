package com.envylabs.cautiousengine.javalin

import com.cautiousengine.address.Address
import com.cautiousengine.address.RandomAddressGenerator

class AddressesHandler(
    private val randomAddressGenerator: RandomAddressGenerator
) {

    fun getAll(): AddressesResponse {
        val addresses = randomAddressGenerator
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

        return AddressesResponse(addresses = addresses)
    }
}
