object Day01 {
    fun part1(input: List<String>): Int {
        require(input.isNotEmpty())
        return input.sumOf { line ->
            val firstDigit = line.first(Char::isDigit)
            val lastDigit = line.last(Char::isDigit)
            "$firstDigit$lastDigit".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }
}

fun main() {
    val input = readInput("Day01")
    Day01.part1(input).println()
    Day01.part2(input).println()
}
