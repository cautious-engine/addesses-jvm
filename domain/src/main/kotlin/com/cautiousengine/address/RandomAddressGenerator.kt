package com.cautiousengine.address

interface RandomAddressGenerator {
    fun generate(min: Int = 0, max: Int = 10): List<Address>
}
