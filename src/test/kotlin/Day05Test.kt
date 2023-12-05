import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe

class Day05Test : ChallengeTest<Long>(Day05) {

    init {
        "part 1" - {
            "seed to location with single number mapping" {
                val input = """
                    seeds: 1
                    
                    seed-to-location map:
                    50 1 1
                """.trimIndent()

                part1(input) shouldBeExactly 50
            }

            "seed to location with range number mapping" {
                val input = """
                    seeds: 5
                    
                    seed-to-location map:
                    50 1 10
                """.trimIndent()

                part1(input) shouldBeExactly 54
            }

            "seed to location which is not in range of mapping" {
                val input = """
                    seeds: 10
                    
                    seed-to-location map:
                    50 52 5
                """.trimIndent()

                part1(input) shouldBeExactly 10
            }

            "seed to location with two mappings" {
                // 1 -> 50, 2 -> 51 => 5 -> 54
                // 54 -> 98
                val input = """
                    seeds: 5

                    seed-to-water map:
                    50 1 10

                    water-to-location map:
                    98 54 3
                """.trimIndent()

                part1(input) shouldBeExactly 98
            }

            "seeds to location with two mappings, choose the lowest one" {
                // 1 -> 50, 2 -> 51 => 5 -> 54
                // 54 -> 98
                // ;
                // 53 -> 53 -> 53
                val input = """
                    seeds: 5 53

                    seed-to-water map:
                    50 1 10

                    water-to-location map:
                    98 54 3
                """.trimIndent()

                part1(input) shouldBeExactly 53
            }

            "part example" {
                val input = """
                    seeds: 79 14 55 13

                    seed-to-soil map:
                    50 98 2
                    52 50 48

                    soil-to-fertilizer map:
                    0 15 37
                    37 52 2
                    39 0 15

                    fertilizer-to-water map:
                    49 53 8
                    0 11 42
                    42 0 7
                    57 7 4

                    water-to-light map:
                    88 18 7
                    18 25 70

                    light-to-temperature map:
                    45 77 23
                    81 45 19
                    68 64 13

                    temperature-to-humidity map:
                    0 69 1
                    1 0 69

                    humidity-to-location map:
                    60 56 37
                    56 93 4
                """.trimIndent()

                part1(input) shouldBeExactly 35
            }

            "big numbers" {
                val input = """
                    seeds: 2276375722
                    
                    seed-to-location map:
                    50 52 5
                """.trimIndent()

                part1(input) shouldBe 2276375722L
            }
        }
    }
}
