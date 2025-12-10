package com.navarrorncf.secretSanta

/**
 * Takes a max value and a seed and returns a list of integers from zero up to
 * the value of 'max' argument (non-inclusive). The algorithm is deterministic.
 */
fun getShuffledList(max: Int, seed: Double): List<Int> {
    val range = 0..<max
    val picks = mutableListOf<Int>()
    val available = range.toMutableList()
    val generateSkipVal = nextFromSeed(seed)

    while (available.isNotEmpty()) {
        val pick = nextIntInRange(available, generateSkipVal.next())
        picks.add(pick)
        available.remove(pick)
    }

    return picks
}

/**
 * This function deterministically gets an item from the list whose index is
 * computed from the input received.
 */
private fun nextIntInRange(available: List<Int>, skip: Int): Int {
    return available[(1 + skip) % available.size]
}

/**
 * This function gets a Double (ideally from Random.nextDouble()) and turns it
 * into a random seed and returns a generator of pseudorandom numbers. This
 * function is deterministic and this is important for the unit testing of the
 * name drawing algorithm.
 */
private fun nextFromSeed(seedNumber: Double): Iterator<Int> {
    val seed = seedNumber
        .toString()
        .replace("-", "")
        .replace(".", "")
    var curIndex = 0

    return iterator {
        while (true) {
            if (curIndex >= seed.length) curIndex = 0
            yield(seed[curIndex++].toString().toInt())
        }
    }
}
