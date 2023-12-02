object Day02 : Challenge<Int>(day = 2) {

    override fun solveFirstPart(input: Input): Int {
        val elfBag = ElfBag(
            maxCubesDisplay = mapOf(
                CubeDisplayColor.RED to 12,
                CubeDisplayColor.GREEN to 13,
                CubeDisplayColor.BLUE to 14,
            ),
        )

        return input
            .map(::parseGame)
            .sumOf { game -> findGameValue(elfBag, game) }
    }

    override fun solveSecondPart(input: Input): Int {
        return input
            .asSequence()
            .map(::parseGame)
            .map(ElfBag.Companion::findRequiredSizeFromGame)
            .map(ElfBag::maxCubesDisplay)
            .map(CubesDisplay::power)
            .sum()
    }

    private fun findGameValue(elfBag: ElfBag, game: Game): Int = if (elfBag.isGamePossible(game)) {
        game.number
    } else {
        0
    }

    private fun parseGame(string: String): Game {
        val (gameString, cubeDisplaysString) = string.split(':')
            .map(String::trim)
        val gameNumber = gameString.takeLastWhile(Char::isDigit).toInt()
        val cubeDisplays = cubeDisplaysString.split(';')
            .map(::parseCubesDisplay)
        return Game(
            number = gameNumber,
            cubesDisplays = cubeDisplays,
        )
    }

    private fun parseCubesDisplay(string: String): CubesDisplay {
        return string.split(',')
            .map(String::trim)
            .associate(::parseCubeDisplay)
    }

    private fun parseCubeDisplay(cubes: String): CubeDisplay {
        val (countString, colorName) = cubes.split(' ')
        val color = CubeDisplayColor.fromName(colorName)
        val count = countString.toInt()
        return color to count
    }
}

private class Game(
    val number: Int,
    val cubesDisplays: List<CubesDisplay>,
)

private class ElfBag(
    val maxCubesDisplay: CubesDisplay,
) {

    fun isGamePossible(game: Game): Boolean {
        return game.cubesDisplays.all(::isDisplayPossible)
    }

    fun isDisplayPossible(display: CubesDisplay): Boolean {
        return CubeDisplayColor.entries.all { color ->
            display.getCubesCount(color) <= maxCubesDisplay.getCubesCount(color)
        }
    }

    companion object {
        fun findRequiredSizeFromGame(game: Game): ElfBag {
            return ElfBag(
                maxCubesDisplay = CubeDisplayColor.entries.associateWith { color ->
                    findRequiredSizeForColor(game, color)
                },
            )
        }

        private fun findRequiredSizeForColor(
            game: Game,
            color: CubeDisplayColor,
        ): Int {
            return game.cubesDisplays.maxOf { cubesDisplay ->
                cubesDisplay.getCubesCount(color)
            }
        }
    }
}

private typealias CubeDisplay = Pair<CubeDisplayColor, Int>
private typealias CubesDisplay = Map<CubeDisplayColor, Int>

private enum class CubeDisplayColor(val colorName: String) {
    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    ;

    companion object {
        fun fromName(name: String) = CubeDisplayColor.entries
            .first { it.colorName == name }
    }
}

private fun CubesDisplay.getCubesCount(color: CubeDisplayColor): Int {
    return getOrDefault(color, 0)
}

private fun CubesDisplay.power(): Int {
    return CubeDisplayColor.entries.fold(initial = 1) { product, color ->
        product * getCubesCount(color)
    }
}

fun main() = Day02.executeParts()
