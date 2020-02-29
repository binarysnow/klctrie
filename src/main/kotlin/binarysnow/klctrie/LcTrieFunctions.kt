package binarysnow.klctrie

/**
 * Calculate the prefix length we can use
 */
fun computePrefix(low: Int, high:Int, ignore: Byte = 0): Byte {
    var count: Byte = 0
    while(extractBits(low, (count + ignore).toByte()) == extractBits(high, (count + ignore).toByte())) ++count
    return count
}

/**
 * Extract bits from a 32 bit number, starting at the given position, index starts at zero
 */
fun extractBits(input: Int, position: Byte, numBits: Byte = 1): Int {
    return input.shl(position.toInt()).ushr(32 - numBits)
}

/**
 * Compute the branch value for an input list
 */
fun <T> computeBranch(input: List<Input<T>>, ignore: Byte, fillFactor: Float = 1f): Byte {
    val size = input.size

    // If there is only one element use zero bits for branching as this is a leaf node
    if (size == 1) return 0
    // If there are only two elements use one bit for branching
    if (size == 2) return 1

    var bits: Byte = 1
    var count: Int

    do {
        bits++
        if (size < ( fillFactor * (1.shl(bits.toInt()))) || ignore + bits > 32) {
            break
        }
        var i = 0
        var pattern = 0
        count = 0
        while (pattern < 1.shl(bits.toInt())) {
            var found = false
            while (i < size && pattern == extractBits(input[i].ip, ignore, bits)) {
                i++
                found = true
            }
            if (found) {
                count++
            }
            pattern++
        }
    } while (count >= (fillFactor * (1.shl(bits.toInt()))))

    return (bits - 1).toByte()
}