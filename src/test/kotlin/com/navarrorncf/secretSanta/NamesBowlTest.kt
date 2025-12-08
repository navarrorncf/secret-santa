package com.navarrorncf.secretSanta

import com.navarrorncf.secretSanta.system.SystemProcessHandler
import com.navarrorncf.secretSanta.system.UserInterfaceHandler
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.*

class NamesBowlTest {
    val mockUI = mock<UserInterfaceHandler> {}
    val mockSysProcessHandler = mock <SystemProcessHandler> {}

    @Test
    fun `getNames gets names from ui input`() {
        whenever(mockUI.read()).thenReturn(
            "Name1, Name2, Name3, Name4"
        )

        val names = getNames(mockUI, mockSysProcessHandler)

        assertArrayEquals(
            arrayOf("Name1", "Name2", "Name3", "Name4"),
            names
        )
    }

    @Test
    fun `rejects blank strings`() {
        whenever(mockUI.read()).thenReturn("")

        getNames(mockUI, mockSysProcessHandler)

        val captor = argumentCaptor<String>()
        verify(mockUI, times(3))
            .write(captor.capture())

        assertTrue(captor.allValues[2].contains("Name list cannot be empty."))
    }

    @Test
    fun `rejects lists with too few names`() {
        whenever(mockUI.read()).thenReturn("Name1, Name2")

        getNames(mockUI, mockSysProcessHandler)

        val captor = argumentCaptor<String>()
        verify(mockUI, times(3))
            .write(captor.capture())

        assertTrue(captor.allValues[2].contains("Name list should contain 3 to 10 participants."))
    }

    @Test
    fun `rejects lists with too many names`() {
        whenever(mockUI.read()).thenReturn("N1,N2,N3,N4,N5,N6,N7,N8,N9,N10,N11")

        getNames(mockUI, mockSysProcessHandler)

        val captor = argumentCaptor<String>()
        verify(mockUI, times(3))
            .write(captor.capture())

        assertTrue(captor.allValues[2].contains("Name list should contain 3 to 10 participants."))
    }

    @Test
    fun `rejects lists with repeating names`() {
        whenever(mockUI.read()).thenReturn("Name1, Name2, Name3, Name1 , Name4, Name3")

        getNames(mockUI, mockSysProcessHandler)

        val captor = argumentCaptor<String>()
        verify(mockUI, times(3))
            .write(captor.capture())

        println(captor.allValues[2])

        assertTrue(captor.allValues[2].contains("Names should not repeat."))
        assertTrue(captor.allValues[2].contains("These names appear more than once: Name1, Name3"))
    }
}