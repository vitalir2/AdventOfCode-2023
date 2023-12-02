import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.ints.shouldBeExactly

class Day01Test : ChallengeTest<Int>(Day01) {

    init {
        "part 1" - {
            "one line" {
                val input = "hell2oo4"

                part1(input) shouldBeExactly 24
            }
            "one line with single number" {
                val input = "swee8t"

                part1(input) shouldBeExactly 88
            }
            "test input (many lines)" {
                val input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
            """.trimIndent()

                part1(input) shouldBeExactly 142
            }
        }
        "part 2" - {
            "cannot be empty" {
                val input = ""

                shouldThrowAny { part2(input) }
            }
            "one line" {
                val input = "helltwooofour"

                part2(input) shouldBeExactly 24
            }
            "one line with single number" {
                val input = "sweeeightt"

                part2(input) shouldBeExactly 88
            }
            "one line with many possible digits with spelling" {
                val input = "eightwothree"

                part2(input) shouldBeExactly 83
            }
            "one line with many possible digits in different forms" {
                val input = "eighthree9"

                part2(input) shouldBeExactly 89
            }
            "test input (many lines)" {
                val input = """
                    two1nine
                    eightwothree
                    abcone2threexyz
                    xtwone3four
                    4nineeightseven2
                    zoneight234
                    7pqrstsixteen
                """.trimIndent()

                part2(input) shouldBeExactly 281
            }
        }
    }
}
