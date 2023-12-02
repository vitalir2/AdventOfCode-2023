object Day01 : Challenge<Int>(day = 1) {
    override fun solveFirstPart(input: List<String>): Int {
        require(input.isNotEmpty())
        return input.sumOf { line -> line.parseTwoDigitNumberFromChars() }
    }

    override fun solveSecondPart(input: List<String>): Int {
        require(input.isNotEmpty())
        return input.sumOf { line ->
            parseTwoDigitNumberFromSpellingAndChars(line)
        }
    }

    private fun parseTwoDigitNumberFromSpellingAndChars(line: String): Int {
        val firstDigit = line.firstDigitBySpellingOrChar()
        val lastDigit = line.lastDigitBySpellingOrChar()
        return "$firstDigit$lastDigit".toInt()
    }

    private fun String.parseTwoDigitNumberFromChars(): Int {
        val firstDigit = first(Char::isDigit)
        val lastDigit = last(Char::isDigit)
        return "$firstDigit$lastDigit".toInt()
    }

    private fun String.findDigitBySpellingOrChar(
        isLast: Boolean,
    ): Char {
        val digits = mutableListOf<Pair<Digit, Int>>()

        fun findIndex(string: String): Int {
            return if (isLast) lastIndexOf(string) else indexOf(string)
        }
        fun findIndex(char: Char): Int {
            return if (isLast) lastIndexOf(char) else indexOf(char)
        }
        fun List<Pair<Digit, Int>>.selectDigitBy(selector: (Pair<Digit, Int>) -> Int): Digit {
            return if (isLast) {
                maxBy(selector)
            } else {
                minBy(selector)
            }.first
        }

        for (digit in Digit.entries) {
            val digitSpellingIndex = findIndex(digit.spelling)
            if (digitSpellingIndex >= 0) {
                digits.add(digit to digitSpellingIndex)
            }
            val digitCharIndex = findIndex(digit.character)
            if (digitCharIndex >= 0) {
                digits.add(digit to digitCharIndex)
            }
        }

        return digits.selectDigitBy { it.second }.character
    }

    private fun String.firstDigitBySpellingOrChar(): Char {
        return findDigitBySpellingOrChar(isLast = false)
    }

    private fun String.lastDigitBySpellingOrChar(): Char {
        return findDigitBySpellingOrChar(isLast = true)
    }
}

@Suppress("unused") // Used by 'entries'
enum class Digit(val spelling: String, val character: Char) {
    ONE("one", '1'),
    TWO("two", '2'),
    THREE("three", '3'),
    FOUR("four", '4'),
    FIVE("five", '5'),
    SIX("six", '6'),
    SEVEN("seven", '7'),
    EIGHT("eight", '8'),
    NINE("nine", '9'),
}

fun main() = Day01.executeParts()
