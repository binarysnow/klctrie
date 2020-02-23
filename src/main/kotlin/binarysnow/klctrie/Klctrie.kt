package binarysnow.klctrie

fun main(args: Array<String>) {
    // Read file
    val input = readInput()

    // Build LcTrie
    val result = buildLctrie(input, 3, 0, 0)

    // Write result
    println(result)
}

fun readInput(): List<Input> {
    return listOf(
            Input(0b0000_0000000000000000000000000000, 0, "A"),
            Input(0b0001_0000000000000000000000000000, 0, "B"),
            Input(0b00101_000000000000000000000000000, 0, "C"),
            Input(0b010_00000000000000000000000000000, 0, "D"),
            Input(0b0110_0000000000000000000000000000, 0, "E"),
            Input(0b0111_0000000000000000000000000000, 0, "F"),
            Input(0b100_00000000000000000000000000000.toInt(), 0, "G"),
            Input(0b101000_00000000000000000000000000.toInt(), 0, "H"),
            Input(0b101001_00000000000000000000000000.toInt(), 0, "I"),
            Input(0b10101_000000000000000000000000000.toInt(), 0, "J"),
            Input(0b10110_000000000000000000000000000.toInt(), 0, "K"),
            Input(0b10111_000000000000000000000000000.toInt(), 0, "L"),
            Input(0b110_00000000000000000000000000000.toInt(), 0, "M"),
            Input(0b11101000_000000000000000000000000.toInt(), 0, "N"),
            Input(0b11101001_000000000000000000000000.toInt(), 0, "O")
        )
}
