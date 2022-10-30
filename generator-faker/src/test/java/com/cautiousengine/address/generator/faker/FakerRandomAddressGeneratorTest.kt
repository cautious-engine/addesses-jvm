package com.cautiousengine.address.generator.faker

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

internal class FakerRandomAddressGeneratorTest {
    private val generator = FakerRandomAddressGenerator()

    @Nested
    inner class Generate {
        @Test
        fun `generates random addresses`() {
            val result = generator.generate(numAddresses = 1)
            assertTrue(result.isNotEmpty())
        }

        @Test
        fun `generates exact number of addresses using same min and max`() {
            val result = generator.generate(numAddresses = 3)
            assertEquals(3, result.size)
        }

        @Test
        fun `supports returning empty list`() {
            val result = generator.generate(numAddresses = 0)
            assertTrue(result.isEmpty())
        }

        @Test
        fun `defaults 'numAddresses' to 5`() {
            val result = generator.generate(numAddresses = 5)
            assertEquals(5, result.size)
        }

        @Test
        fun `throws InvalidArgumentException when 'numAddresses' is less than 0`() {
            val throwable = assertFails {
                generator.generate(numAddresses = -1)
            }

            assertEquals("'numAddresses' must be >= 0", throwable.message)
        }
    }
}
