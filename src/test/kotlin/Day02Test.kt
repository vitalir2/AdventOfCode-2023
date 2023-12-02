import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly

class Day02Test : FreeSpec({
    "part1" - {
        "possible game" {
            val input = listOf(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            )

            Day02.solveFirstPart(input) shouldBeExactly 1
        }

        "impossible game" {
            val input = listOf(
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            )

            Day02.solveFirstPart(input) shouldBeExactly 0
        }

        "two possible games" {
            val input = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            """.trimIndent().lines()

            Day02.solveFirstPart(input) shouldBeExactly 3
        }

        "possible game with edge values" {
            val input = listOf(
                "Game 3: 8 green, 6 blue, 12 red; 14 blue, 4 red, 13 green; 5 green, 1 red",
            )

            Day02.solveFirstPart(input) shouldBeExactly 3
        }

        "part example" {
            val input = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """.trimIndent().lines()

            Day02.solveFirstPart(input) shouldBeExactly 8
        }

        "high game number" {
            val input = listOf(
                "Game 100: 8 green, 6 blue, 12 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            )

            Day02.solveFirstPart(input) shouldBeExactly 100
        }
    }
})
