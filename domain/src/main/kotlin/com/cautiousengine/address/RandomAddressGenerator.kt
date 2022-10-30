package com.cautiousengine.address

interface RandomAddressGenerator {
    fun generate(numAddresses: Int = 5): List<Address>
}
