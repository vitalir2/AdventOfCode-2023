import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly

class Day01Test : FreeSpec({
    "part1" - {
        "one line" {
            val input = listOf(
                "hell2oo4",
            )
            Day01.solveFirstPart(input) shouldBeExactly 24
        }
        "one line with single number" {
            val input = listOf(
                "swee8t",
            )
            Day01.solveFirstPart(input) shouldBeExactly 88
        }
        "test input (many lines)" {
            val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()
            Day01.solveFirstPart(input) shouldBeExactly 142
        }
    }

    "part 2" - {
        "cannot be empty" {
            val input = emptyList<String>()
            shouldThrowAny { Day01.solveSecondPart(input) }
        }
        "one line" {
            val input = listOf(
                "helltwooofour",
            )
            Day01.solveSecondPart(input) shouldBeExactly 24
        }
        "one line with single number" {
            val input = listOf(
                "sweeeightt",
            )
            Day01.solveSecondPart(input) shouldBeExactly 88
        }
        "one line with many possible digits with spelling" {
            val input = listOf(
                "eightwothree",
            )
            Day01.solveSecondPart(input) shouldBeExactly 83
        }
        "one line with many possible digits in different forms" {
            val input = listOf(
                "eighthree9",
            )
            Day01.solveSecondPart(input) shouldBeExactly 89
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
            """.trimIndent().lines()
            Day01.solveSecondPart(input) shouldBeExactly 281
        }
    }
})
