import kotlin.math.pow

object Day04 : Challenge<Int>(day = 4) {

    private val parseNumbersRegex = " +".toRegex()

    override fun solveFirstPart(input: Input): Int {
        return input
            .map(::parseCard)
            .sumOf(ScratchCard::points)
    }

    override fun solveSecondPart(input: Input): Int {
        return input
            .map(::parseCard)
            .let(::processCards)
    }

    private fun processCards(cards: List<ScratchCard>): Int {
        val cardIdToCopiesCount = mutableMapOf<Int, Int>()
        for (card in cards) {
            cardIdToCopiesCount[card.id] = 1
        }

        for (card in cards) {
            repeat(card.numberOfWins) { offset ->
                cardIdToCopiesCount.computeIfPresent(card.id + offset + 1) { _, oldCount ->
                    oldCount + cardIdToCopiesCount.getValue(card.id)
                }
            }
        }

        return cardIdToCopiesCount.map { it.value }.sum()
    }

    private fun parseCard(string: String): ScratchCard {
        val (winningNumbersString, numbersYouHaveString) = string
            .substringAfter(": ").split(" | ")
        val cardId = string
            .substringBefore(": ")
            .takeLastWhile(Char::isDigit)
            .toInt()
        val winningNumbers = parseNumbers(winningNumbersString).toSet()
        val numbersYouHave = parseNumbers(numbersYouHaveString)
        return ScratchCard(
            id = cardId,
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

class ScratchCard(
    val id: Int,
    val winningNumbers: Set<Int>,
    val numbers: List<Int>,
) {

    val numberOfWins: Int = numbers.count(::isNumberWinning)

    private fun isNumberWinning(number: Int): Boolean {
        return number in winningNumbers
    }
}

private val ScratchCard.points: Int
    get() {
        val numberOfWins = numberOfWins
        return if (numberOfWins > 0) {
            2f.pow(numberOfWins - 1).toInt()
        } else {
            0
        }
    }

fun main() = Day04.executeParts()
