import io.kotest.matchers.ints.shouldBeExactly

class Day03Test : ChallengeTest<Int>(day = Day03) {

    init {
        "part 1" - {
            "part number — adjacent vertically" {
                val input = """
                    46.
                    *..
                """.trimIndent()

                part1(input) shouldBeExactly 46
            }

            "part number — adjacent horizontally" {
                val input = """
                    .81*
                    ....
                """.trimIndent()

                part1(input) shouldBeExactly 81
            }

            "no part number" {
                val input = """
                    .81.
                    ....
                """.trimIndent()

                part1(input) shouldBeExactly 0
            }

            "part number — adjacent diagonally" {
                val input = """
                    ..*.
                    12..
                """.trimIndent()

                part1(input) shouldBeExactly 12
            }

            "part number — adjacent vertically in center" {
                val input = """
                    ..#..
                    12345
                """.trimIndent()

                part1(input) shouldBeExactly 12345
            }

            "result = sum of only part numbers" {
                val input = """
                    ....9
                    12*85
                """.trimIndent()

                part1(input) shouldBeExactly 97
            }

            "part example" {
                val input = """
                    467..114..
                    ...*......
                    ..35..633.
                    ......#...
                    617*......
                    .....+.58.
                    ..592.....
                    ......755.
                    ...${'$'}.*....
                    .664.598..
                """.trimIndent()

                part1(input) shouldBeExactly 4361
            }
        }
    }
}
