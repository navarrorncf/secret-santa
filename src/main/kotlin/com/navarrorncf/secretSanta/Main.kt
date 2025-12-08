package com.navarrorncf.secretSanta

import com.navarrorncf.secretSanta.system.SystemProcessHandler
import com.navarrorncf.secretSanta.system.UserInterfaceHandler

fun main() {
    val input = getNames(UserInterfaceHandler(), SystemProcessHandler())
    println(input.joinToString("\n"))
}