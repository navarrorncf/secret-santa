package com.navarrorncf.secretSanta

fun assignPicks(names: Array<String>, picks: List<Int>): Map<String, String> {
    val assignments = mutableMapOf<String, String>()
    val picksWrapAround = picks + picks[0]

    picksWrapAround.forEachIndexed { i, pick ->
        if (i == 0) return@forEachIndexed

        val person = names[picks[i-1]]
        val secretSanta = names[pick]
        assignments[person] = secretSanta
    }

    return assignments
}