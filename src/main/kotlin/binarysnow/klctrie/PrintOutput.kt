package binarysnow.klctrie

class PrintOutput(private val input: List<Input<String>>) {
    private val prefixRecords = mutableListOf<PrefixRecord>()
    private val lcTrieBuilder = LCTrieBuilder<PrefixRecord>()

    private var count = 0

    data class PrefixRecord(val index: Int, val prefix: Int, val data: String)

    fun printLcTrie() {
        val data = buildPrefixRecords()

        val result = lcTrieBuilder.buildLcTrie(data, 3, 0, 0)

        printNodes(result)
    }

    private fun buildPrefixRecords(): List<Input<PrefixRecord>> {
        val result = mutableListOf<Input<PrefixRecord>>()
        input.forEachIndexed { index, input ->
            val record = PrefixRecord(index, input.ip, input.data)
            prefixRecords.add(record)
            result.add(Input(input.ip, input.length, record))
        }
        return result
    }

    private fun printNodes(rootNode: Node<PrefixRecord>) {
        var ptr = 1
        var children = listOf(rootNode)

        while (children.isNotEmpty()) {
            children = printNodes(children, ptr)
            ptr += children.size
        }
    }

    private fun printNodes(nodes: List<Node<PrefixRecord>>, ptr: Int): List<Node<PrefixRecord>> {
        val result = mutableListOf<Node<PrefixRecord>>()
        var pointer = 0
        var index = 0
        for (node in nodes) {
            if (node.children.size > 1) {
                pointer = ptr + index
                index += node.children.size
            }
            println("${count}\t${node.branchBits}, ${node.skipBits}, ${node.data?.index ?: pointer}")
            count++
            result.addAll(node.children)
        }
        return result
    }
}