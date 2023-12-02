import io.kotest.core.spec.style.FreeSpec

abstract class ChallengeTest<T>(
    private val day: Challenge<T>
) : FreeSpec() {

    fun part1(input: String): T {
        return day.solveFirstPart(input.toInput())
    }

    fun part2(input: String): T {
        return day.solveSecondPart(input.toInput())
    }

    private fun String.toInput(): Input {
        return if (isEmpty()) emptyList() else lines()
    }
}
