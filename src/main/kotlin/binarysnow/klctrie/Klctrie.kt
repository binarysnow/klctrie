package binarysnow.klctrie

fun main() {
    // Read file
    val input = readInput()

    // Build LcTrie
    val lcTrieBuilder = LCTrieBuilder<String>()
    val result = lcTrieBuilder.buildLcTrie(input, 3, 0, 0)

    // Write result
    println(result)

    val printer = PrintOutput(input)
    printer.printLcTrie()
}

fun readInput(): List<Input<String>> {
    return listOf(
            Input(0b0000_0000000000000000000000000000, 4, "A"),
            Input(0b0001_0000000000000000000000000000, 4, "B"),
            Input(0b00101_000000000000000000000000000, 5, "C"),
            Input(0b010_00000000000000000000000000000, 3, "D"),
            Input(0b0110_0000000000000000000000000000, 4, "E"),
            Input(0b0111_0000000000000000000000000000, 4, "F"),
            Input(0b100_00000000000000000000000000000.toInt(), 3, "G"),
            Input(0b101000_00000000000000000000000000.toInt(), 6, "H"),
            Input(0b101001_00000000000000000000000000.toInt(), 6, "I"),
            Input(0b10101_000000000000000000000000000.toInt(), 5, "J"),
            Input(0b10110_000000000000000000000000000.toInt(), 5, "K"),
            Input(0b10111_000000000000000000000000000.toInt(), 5, "L"),
            Input(0b110_00000000000000000000000000000.toInt(), 3, "M"),
            Input(0b11101000_000000000000000000000000.toInt(), 8, "N"),
            Input(0b11101001_000000000000000000000000.toInt(), 8, "O")
        )
}
