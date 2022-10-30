package com.cautiousengine.address.generator.faker

import com.cautiousengine.address.Address
import com.cautiousengine.address.RandomAddressGenerator
import com.github.javafaker.Faker

class FakerRandomAddressGenerator : RandomAddressGenerator {
    private val faker = Faker()

    override fun generate(numAddresses: Int): List<Address> {
        require(numAddresses >= 0) { "'numAddresses' must be >= 0" }

        val addresses = mutableListOf<Address>()
        repeat(numAddresses) {
            addresses.add(generateAddress())
        }

        return addresses.toList()
    }

    private fun generateAddress(): Address {
        val address = faker.address()
        val stateAbbr = address.stateAbbr()

        return Address(
            street = address.streetAddress(),
            city = address.city(),
            state = stateAbbr,
            zipCode = address.zipCodeByState(stateAbbr)
        )
    }
}
