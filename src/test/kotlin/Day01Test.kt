import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly

class Day01Test : FreeSpec({
    "part1" - {
        "one line" {
            val input = listOf(
                "hell2oo4",
            )
            Day01.part1(input) shouldBeExactly 24
        }
        "one line with single number" {
            val input = listOf(
                "swee8t",
            )
            Day01.part1(input) shouldBeExactly 88
        }
        "test input (many lines)" {
            val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()
            Day01.part1(input) shouldBeExactly 142
        }
    }
})
