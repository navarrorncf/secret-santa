package com.navarrorncf.secretSanta

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SecretSantaAssignerTest {
    @Test
    fun `Assigns picks accordingly`() {
        val result = assignPicks(
            arrayOf("name1", "name2", "name3", "name4"),
            listOf(3, 2, 1, 0)
        )

        assertEquals("name4", result["name1"])
        assertEquals("name1", result["name2"])
        assertEquals("name2", result["name3"])
        assertEquals("name3", result["name4"])
    }

}