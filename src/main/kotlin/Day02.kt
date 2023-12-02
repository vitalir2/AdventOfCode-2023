object Day02 : Challenge<Int>(day = 2) {

    override fun solveFirstPart(input: List<String>): Int {
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

    private fun findGameValue(elfBag: ElfBag, game: Game): Int = if (elfBag.isGamePossible(game)) {
        game.number
    } else {
        0
    }

    override fun solveSecondPart(input: List<String>): Int {
        return 0
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

fun main() = Day02.executeParts()
