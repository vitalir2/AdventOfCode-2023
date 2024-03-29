import io.kotest.matchers.ints.shouldBeExactly

class Day02Test : ChallengeTest<Int>(Day02) {
    init {
        "part 1" - {
            "possible game" {
                val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

                part1(input) shouldBeExactly 1
            }
            "impossible game" {
                val input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"

                part1(input) shouldBeExactly 0
            }
            "two possible games" {
                val input = """
                    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                """.trimIndent()

                part1(input) shouldBeExactly 3
            }
            "possible game with edge values" {
                val input = "Game 3: 8 green, 6 blue, 12 red; 14 blue, 4 red, 13 green; 5 green, 1 red"

                part1(input) shouldBeExactly 3
            }
            "part example" {
                val input = """
                    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """.trimIndent()

                part1(input) shouldBeExactly 8
            }
            "high game number" {
                val input = "Game 100: 8 green, 6 blue, 12 red; 5 blue, 4 red, 13 green; 5 green, 1 red"

                part1(input) shouldBeExactly 100
            }
        }

        "part 2" - {
            "example game" {
                val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

                part2(input) shouldBeExactly 48
            }
            "game with no cubes of some color" {
                val input = "Game 3: 8 green, 6 blue; 5 blue, 13 green; 5 green"

                part2(input) shouldBeExactly 0
            }
            "take the max of all displays and multiplies them to get the power of a set of cubes" {
                val input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"

                part2(input) shouldBeExactly 20 * 13 * 6
            }
            "many games => take a sum of their powers (part example)" {
                val input = """
                    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """.trimIndent()

                part2(input) shouldBeExactly 2286
            }
        }
    }
}
