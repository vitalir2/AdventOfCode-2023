import kotlin.io.path.Path
import kotlin.io.path.readLines

abstract class Challenge<T>(private val day: Int) {

    abstract fun solveFirstPart(input: List<String>): T

    abstract fun solveSecondPart(input: List<String>): T

    fun part1() = partN(n = 1)

    fun part2() = partN(n = 2)

    fun executeParts() {
        part1()
        part2()
    }

    private fun partN(n: Int) {
        val input = readInput()
        val solution = if (n == 1) solveFirstPart(input) else solveSecondPart(input)
        println(solution)
    }

    private fun readInput(): List<String> {
        val dayName = if (day < 10) "0$day" else day.toString()
        return Path("src/main/kotlin/Day$dayName.txt").readLines()
    }
}
