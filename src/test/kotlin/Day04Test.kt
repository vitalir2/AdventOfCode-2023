import io.kotest.matchers.ints.shouldBeExactly

class Day04Test : ChallengeTest<Int>(day = Day04) {

    init {
        "part 1" - {
            "card logic" - {
                "no winning numbers" {
                    val input = """
                        Card 1: 41 48 83 86 17 | 84 87  6 31 19  9 42 53
                    """.trimIndent()

                    part1(input) shouldBeExactly 0
                }

                "one winning number => 1 point" {
                    val input = """
                        Card 1: 41 48 83 86 17 | 83 87  6 31 19  9 42 53
                    """.trimIndent()

                    part1(input) shouldBeExactly 1
                }

                "two winning numbers => 2 points" {
                    val input = """
                        Card 1: 41 48 83 86 17 | 83 87  6 31 17  9 42 53
                    """.trimIndent()

                    part1(input) shouldBeExactly 2
                }

                "four winning numbers => 8 points <=> 2.pow(win_nums - 1)" {
                    val input = """
                        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    """.trimIndent()

                    part1(input) shouldBeExactly 8
                }
            }

            "result of many cards with winning numbers = sum of their points" {
                val input = """
                        Card 1: 41 42 | 83 85 41
                        Card 2: 9 63 | 63 9 9
                    """.trimIndent()

                part1(input) shouldBeExactly 5
            }

            "part example" {
                val input = """
                        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """.trimIndent()

                part1(input) shouldBeExactly 13
            }
        }

        "part 2" - {
            "only one card with no copies" {
                val input = """
                    Card 1: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """.trimIndent()

                part2(input) shouldBeExactly 1
            }

            "two cards with no copies" {
                val input = """
                    Card 1: 41 47 82 86 18 | 83 85  6 31 17  9 48 53
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                """.trimIndent()

                part2(input) shouldBeExactly 2
            }

            "a first card creates copy of the second one" {
                val input = """
                    Card 1: 41 48 83 86 17 | 83 87  6 31 19  9 42 53
                    Card 2: 13 31 20 16 59 | 61 30 68 82 17 32 24 19
                """.trimIndent()

                part2(input) shouldBeExactly 3
            }

            "a first card creates many copies of next ones" {
                val input = """
                    Card 1:  9 63 | 63  9  9
                    Card 2:  1  2 |  9 55 91
                    Card 3: 84 12 |  1 15 32
                """.trimIndent()

                part2(input) shouldBeExactly 5
            }

            "a card with copies creates new copies from the all card instances" {
                val input = """
                    Card 1:  9 63 | 63  9  9
                    Card 2: 55  2 |  9 55 91
                    Card 3: 84 12 |  1 15 32
                """.trimIndent()

                part2(input) shouldBeExactly 1 + 2 + 4
            }

            "part example" {
                val input = """
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """.trimIndent()

                part2(input) shouldBeExactly 30
            }
        }
    }
}
