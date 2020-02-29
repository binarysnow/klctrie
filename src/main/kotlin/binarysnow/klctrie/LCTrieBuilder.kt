package binarysnow.klctrie

class LCTrieBuilder<T> {
    /**
     * Build an LCTrie
     */
    fun buildLcTrie(input: List<Input<T>>, branch: Byte, skip: Byte, ignore: Byte, fillFactor: Float = 1f): Node<T> {
        val children = mutableListOf<Node<T>>()
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
            when {
                subList.size > 1 -> {
                    val newSkip = computeSkip(subList, newIgnore)
                    val newBranch = computeBranch(subList, (newIgnore + newSkip).toByte(), fillFactor)
                    children.add(buildLcTrie(subList, newBranch, newSkip, newIgnore))
                }
                subList.size == 1 -> {
                    // This is a leaf node so has a branch of zero
                    // The skip is ...?
                    val first = subList.first()
                    val newSkip: Byte = (first.length - newIgnore).toByte()
                    children.add(Node(0, newSkip, emptyList(), first.data))
                }
                else -> {
                    // Need to find the record it should point to
                    // Because of the fill-factor, this might be a real node or an empty node
                    println ("FILL FACTOR NODE!!")
                }
            }
            start = i
        }
        return Node(branch, skip, children, null)
    }

    /**
     * Compute the number of skip bits from an input list
     */
    private fun computeSkip(input: List<Input<T>>, ignore: Byte): Byte {
        val low = input.first().ip
        val high = input.last().ip
        return computePrefix(low, high, ignore)
    }
}
