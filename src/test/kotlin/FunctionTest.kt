import binarysnow.klctrie.Input
import binarysnow.klctrie.computeBranch
import binarysnow.klctrie.computePrefix
import binarysnow.klctrie.extractBits
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class FunctionTest {
    @Test
    fun prefixTest() {
        val result = computePrefix(0b000000.shl(32-6), 0b000111.shl(32-6))
        assertThat(result, Matchers.equalTo(3.toByte()))
    }

    @Test
    fun prefixTest2() {
        val result = computePrefix(0b000000.shl(32-6), 0b100111.shl(32-6))
        assertThat(result, Matchers.equalTo(0.toByte()))
    }

    @Test
    fun extractBitTest() {
        val result = extractBits(0b00010000_00000000_00001000_00000000, 3)
        assertThat(result, Matchers.equalTo(1))
    }

    @Test
    fun computeBranchTest1() {
        val input = listOf(
            Input(0b000_0, 0, ""),
            Input(0b000_1, 0, ""))
        val result = computeBranch(input, 3)
        assertThat(result.toInt(), Matchers.equalTo(1))
    }

    @Test
    fun computeBranchTest2() {
        val input = listOf(
            Input(0b000_00, 0, ""),
            Input(0b000_01, 0, ""),
            Input(0b000_10, 0, ""),
            Input(0b000_11, 0, "")
            )
        val result = computeBranch(input, 3)
        assertThat(result.toInt(), Matchers.equalTo(2))
    }

    @Test
    fun computeBranchTest0() {
        val input = listOf(
            Input(0b000_00, 0, "")
        )
        val result = computeBranch(input, 3)
        assertThat(result.toInt(), Matchers.equalTo(0))
    }
}