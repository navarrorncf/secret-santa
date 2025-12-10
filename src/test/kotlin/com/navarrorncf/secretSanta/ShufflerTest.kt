package com.navarrorncf.secretSanta

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.CsvSource

class ShufflerTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "8,0.187348723093596,1 3 5 6 0 7 4 2",
            "10,1.9237954789982,2 1 5 7 4 0 8 9 3 6",
            "12,0.87265049734095,1 10 9 4 11 8 2 0 6 7 3 5",
            "20,0.1234567890,1 3 5 7 9 11 13 15 17 19 2 6 10 14 18 4 16 12 8 0",
            "30,0.92837646129,1 11 4 12 6 13 10 8 15 3 7 21 2 23 14 24 17 25 22 19 27 9 18 20 5 0 29 16 26 28",
        ]
    )
    fun `returns list in expected order`(max: Int, seed: Double, expected: String) {
        assertEquals(
            expected,
            getShuffledList(max, seed).joinToString(" ")
        )
    }

    @RepeatedTest(value = 10, failureThreshold = 1)
    fun `result is deterministic`() {
        assertEquals(
            "1 3 10 5 13 11 7 16 12 2 8 14 17 23 19 4 9 25 18 28 26 21 31 27 6 22 29 32 38 34 15 24 40 33 43 41 36 46 42 20 37 44 47 39 0 35 48 49 45 30",
            getShuffledList(50, 0.17286385023495).joinToString(" ")
        )
    }
}