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

fun computeBranch(input: List<Input>, ignore: Byte): Byte {
    val size = input.size

    // If there is only one element use zero bits for branching as this is a leaf node
    if (size == 1) return 0
    // If there are only two elements use one bit for branching
    if (size == 2) return 1

    var bits: Byte = 1
    var count: Int

    do {
        bits++
        if (size < 1.shl(bits.toInt()) || ignore + bits > 32) {
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
    } while (count >= 1.shl(bits.toInt()))

    return (bits - 1).toByte()
}

/**
 * Compute the number of skip bits from an input list
 */
fun computeSkip(input: List<Input>, ignore: Byte): Byte {
    val low = input.first().ip
    val high = input.last().ip
    return computePrefix(low, high, ignore)
}

fun buildLctrie(input: List<Input>, branch: Byte, skip: Byte, ignore: Byte): Node {
    val children = mutableListOf<Node>()
    val size = input.size
    var start = 0
    var i = 0
    for (bitPattern in 0 until 1.shl(branch.toInt())) {
        // Find the number of elements matching the bit pattern so we can create the sublist
        while(i < size && extractBits(input[i].ip, (ignore + skip).toByte(), branch) == bitPattern) {
            i++
        }
        val subList = input.subList(start, i)
        val newIgnore = (ignore + branch + skip).toByte()
        if (subList.size > 1) {
            val newSkip = computeSkip(subList, newIgnore)
            val newBranch = computeBranch(subList, (newIgnore + newSkip).toByte())
            children.add(buildLctrie(subList, newBranch, newSkip, newIgnore))
        } else {
            // This is a leaf node so has a branch of zero
            // The skip is ...?
            val first = subList.first()
            val newSkip: Byte = (first.length - newIgnore).toByte()
            children.add(Node(0, newSkip, emptyList(), first.info))
        }
        start = i
    }
    return Node(branch, skip, children)
}

fun printNodes(input: Node) {
    var ptr = 1
    var children = listOf(input)

    while (children.isNotEmpty()) {
        children = printNodes(children, ptr)
        ptr += children.size
    }
}

fun printNodes(input: List<Node>, ptr: Int): List<Node> {
    val result = mutableListOf<Node>()
    var pointer: Int
    for (node in input) {
        if (node.children.size < 2) {
            pointer = 0
        } else {
            pointer = ptr
        }
        println("${node.branchBits}, ${node.skipBits}, $pointer")
        result.addAll(node.children)
    }
    return result
}