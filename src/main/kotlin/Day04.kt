import kotlin.math.pow

object Day04 : Challenge<Int>(day = 4) {
    override fun solveFirstPart(input: Input): Int {
        return input
            .map(::parseCard)
            .sumOf(ScratchCard::points)
    }

    override fun solveSecondPart(input: Input): Int {
        TODO("Not yet implemented")
    }

    private fun parseCard(string: String): ScratchCard {
        val (winningNumbersString, numbersYouHaveString) = string
            .substringAfter(": ").split(" | ")
        val winningNumbers = parseNumbers(winningNumbersString).toSet()
        val numbersYouHave = parseNumbers(numbersYouHaveString)
        return ScratchCard(
            winningNumbers = winningNumbers,
            numbers = numbersYouHave,
        )
    }

    private fun parseNumbers(string: String): List<Int> {
        return string
            .split(parseNumbersRegex)
            .map(String::trim)
            .filter(String::isNotBlank)
            .map(String::toInt)
    }
}

private val parseNumbersRegex = " +".toRegex()

class ScratchCard(
    val winningNumbers: Set<Int>,
    val numbers: List<Int>,
) {

    val points: Int
        get() {
            val numberOfWinningNumbers = numbers.count(::isNumberWinning)
            return if (numberOfWinningNumbers > 0) {
                2f.pow(numberOfWinningNumbers - 1).toInt()
            } else {
                0
            }
        }

    private fun isNumberWinning(number: Int): Boolean {
        return number in winningNumbers
    }
}

fun main() = Day04.part1()
