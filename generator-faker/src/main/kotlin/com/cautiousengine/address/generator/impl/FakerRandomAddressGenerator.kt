package com.cautiousengine.address.generator.impl

import com.cautiousengine.address.Address
import com.cautiousengine.address.RandomAddressGenerator
import com.github.javafaker.Faker
import kotlin.random.Random
import kotlin.random.nextInt

class FakerRandomAddressGenerator : RandomAddressGenerator {
    private val faker = Faker()

    override fun generate(min: Int, max: Int): List<Address> {
        require(min >= 0) { "'min' must be >= 0" }

        val numResults = Random.nextInt(min..max)

        val addresses = mutableListOf<Address>()
        repeat(numResults) {
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
