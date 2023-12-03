object Day03 : Challenge<Int>(day = 3) {
    override fun solveFirstPart(input: Input): Int {
        val schematic = parseSchematic(input)
        return schematic.rows
            .flatMap(EngineSchematic.Row::chars)
            .filterIsInstance<EngineChar.Number>()
            .filter { number -> number.isPartNumber(schematic) }
            .sumOf(EngineChar.Number::intValue)
    }

    override fun solveSecondPart(input: Input): Int {
        val schematic = parseSchematic(input)
        return schematic.rows
            .flatMap(EngineSchematic.Row::chars)
            .filterIsInstance<EngineChar.Symbol>()
            .sumOf { it.gearRatio(schematic) ?: 0 }
    }

    private fun parseSchematic(input: Input): EngineSchematic {
        return input
            .mapIndexed { index, line -> parseEngineSchematicRow(line, index) }
            .let(::EngineSchematic)
    }

    private fun parseEngineSchematicRow(
        line: String,
        lineIndex: Int,
    ): EngineSchematic.Row {
        val chars = buildList {
            for ((index, char) in line.withIndex()) {
                val parsedChar = parseEngineChar(char, index, lineIndex)
                add(parsedChar)
            }
        }
        return EngineSchematic.Row(
            id = lineIndex,
            chars = chars,
        )
    }

    private fun MutableList<EngineChar>.parseEngineChar(
        char: Char,
        indexInRow: Int,
        row: Int
    ): EngineChar {
        return when {
            char == EngineChar.BLANK_SYMBOL -> EngineChar.Blank
            char.isDigit() -> parseNumberChar(char, indexInRow, row)
            else -> EngineChar.Symbol(
                raw = char,
                row = row,
                column = indexInRow,
            )
        }
    }

    private fun MutableList<EngineChar>.parseNumberChar(
        char: Char,
        indexInRow: Int,
        row: Int
    ): EngineChar.Number {
        val lastChar = lastOrNull()
        val numberValue: String
        val startIndex: Int
        if (lastChar is EngineChar.Number) {
            // Parsing the same char, no need to create a copy of the model
            removeLast()
            numberValue = lastChar.value + char
            startIndex = lastChar.startIndex
        } else {
            numberValue = char.toString()
            startIndex = indexInRow
        }
        return EngineChar.Number(
            row = row,
            startIndex = startIndex,
            endIndex = indexInRow,
            value = numberValue,
        )
    }
}

class EngineSchematic(
    val rows: List<Row>,
) {

    init {
        check(rows.isNotEmpty())

        val firstRow = rows.first()
        check(areAllRowSizesEqual(firstRow))
    }

    private fun areAllRowSizesEqual(firstRow: Row) =
        rows.all { it.size == firstRow.size }

    val size: Size = Size(
        width = rows.first().size,
        height = rows.size,
    )

    operator fun get(rowIndex: Int, columnIndex: Int): EngineChar {
        checkRowIndex(rowIndex)
        checkColumnIndex(columnIndex)

        val row = rows[rowIndex]
        return row[columnIndex]
    }

    fun getAdjacent(rowIndex: Int, columnIndex: Int): List<EngineChar> {
        checkRowIndex(rowIndex)
        checkColumnIndex(columnIndex)

        val leftAdj = (columnIndex - 1).coerceAtLeast(0)
        val rightAdj = (columnIndex + 1).coerceAtMost(size.width - 1)
        val topAdj = (rowIndex - 1).coerceAtLeast(0)
        val bottomAdj = (rowIndex + 1).coerceAtMost(size.height - 1)
        return buildList {
            for (horIndex in leftAdj..rightAdj) {
                for (verIndex in topAdj..bottomAdj) {
                    add(get(rowIndex = verIndex, columnIndex = horIndex))
                }
            }
        }
            .sortedBy { char -> char.representation }
            .distinct()
    }

    private fun checkRowIndex(rowIndex: Int) {
        require(rowIndex in 0 until size.height) {
            "Row index must be between 0 and ${size.height - 1} but was $rowIndex"
        }
    }

    private fun checkColumnIndex(columnIndex: Int) {
        require(columnIndex in 0 until size.width) {
            "Column index must be between 0 and ${size.width - 1} but was $columnIndex"
        }
    }

    data class Row(
        val id: Int,
        val chars: List<EngineChar>,
    ) {

        private val rawChars: List<Char> = chars.flatMap { char ->
            when (char) {
                is EngineChar.Blank -> listOf(EngineChar.BLANK_SYMBOL)
                is EngineChar.Number -> char.value.toList()
                is EngineChar.Symbol -> listOf(char.raw)
            }
        }

        private val numbers: List<EngineChar.Number> = chars.filterIsInstance<EngineChar.Number>()

        val size: Int
            get() = rawChars.size

        operator fun get(index: Int): EngineChar {
            val rawChar = rawChars[index]
            return when {
                rawChar.isDigit() -> numbers.first { char ->
                    index in char.startIndex..char.endIndex
                }
                rawChar == EngineChar.BLANK_SYMBOL -> EngineChar.Blank
                else -> EngineChar.Symbol(
                    raw = rawChar,
                    row = id,
                    column = index,
                )
            }
        }
    }
}

sealed interface EngineChar {

    val representation: String

    data object Blank : EngineChar {
        override val representation: String
            get() = BLANK_SYMBOL.toString()
    }
    data class Symbol(
        val row: Int,
        val column: Int,
        val raw: Char,
    ) : EngineChar {
        init {
            check(raw != BLANK_SYMBOL)
        }

        override val representation: String
            get() = raw.toString()
    }
    data class Number(
        val row: Int,
        val startIndex: Int,
        val endIndex: Int,
        val value: String,
    ) : EngineChar {

        override val representation: String
            get() = value

        val intValue: Int
            get() {
                return value.toInt()
            }

        fun isPartNumber(schematic: EngineSchematic): Boolean {
            val leftAdj = (startIndex - 1).coerceAtLeast(0)
            val rightAdj = (endIndex + 1).coerceAtMost(schematic.size.width - 1)
            val topAdj = (row - 1).coerceAtLeast(0)
            val bottomAdj = (row + 1).coerceAtMost(schematic.size.height - 1)
            for (horIndex in leftAdj..rightAdj) {
                for (verIndex in topAdj..bottomAdj) {
                    if (schematic[verIndex, horIndex] is Symbol) {
                        return true
                    }
                }
            }

            return false
        }
    }

    companion object {
        const val BLANK_SYMBOL = '.'
    }
}

fun EngineChar.Symbol.gearRatio(schematic: EngineSchematic): Int? {
    val hasGearSymbol = raw == '*'
    if (hasGearSymbol.not()) return null

    val adjacentNumbers = schematic.getAdjacent(row, column).filterIsInstance<EngineChar.Number>()
    return if (adjacentNumbers.size == 2) {
        adjacentNumbers[0].intValue * adjacentNumbers[1].intValue
    } else {
        null
    }
}

fun main() = Day03.executeParts()
