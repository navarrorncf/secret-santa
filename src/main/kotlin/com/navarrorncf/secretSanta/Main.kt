package com.navarrorncf.secretSanta

import com.navarrorncf.secretSanta.system.*
import kotlin.random.Random

fun main() {
    val names = getNames(UserInterfaceHandler(), SystemProcessHandler())

    val picks = getShuffledList(names.size, Random.nextDouble())

    println(assignPicks(names, picks))
}