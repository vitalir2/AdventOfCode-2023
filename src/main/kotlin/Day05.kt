object Day05 : Challenge<Long>(day = 5) {
    override fun solveFirstPart(input: Input): Long {
        val almanac = parseAlmanac(input)
        return almanac.locationNumbers().min()
    }

    override fun solveSecondPart(input: Input): Long {
        TODO("Not yet implemented")
    }

    private fun parseAlmanac(input: Input): Almanac {
        val seeds = input.first()
            .substringAfter(": ")
            .split(' ')
            .map(String::toLong)

        val mapsLines = input.drop(2)
        val maps = mapsLines.joinToString(separator = "\n")
            .split("\n\n")
            .map(::parseMap)
            .associateBy(AlmanacMap::source)

        return Almanac(
            seeds = seeds,
            maps = maps,
        )
    }

    private fun parseMap(line: String): AlmanacMap {
        val mapLines = line.split("\n")
        val nameLine = mapLines.first()
        val (source, _, destination) = nameLine
            .substringBefore(" map")
            .split('-')

        val entriesLine = mapLines.drop(1)
        val entries: List<AlmanacMap.Entry> = entriesLine
            .map(::parseMapEntry)
        return AlmanacMap(
            source = source,
            destination = destination,
            entries = entries,
        )
    }

    private fun parseMapEntry(line: String): AlmanacMap.Entry {
        val (destination, source, rangeLength) = line
            .split(' ')
            .map(String::toLong)
        return AlmanacMap.Entry(
            sourceRange = source..<source + rangeLength,
            destinationRange = destination..<destination + rangeLength,
        )
    }
}

class Almanac(
    val seeds: List<Seed>,
    val maps: Map<String, AlmanacMap>,
) {

    fun locationNumbers(): List<LocationNumber> {
        return seeds.map(::locationNumber)
    }

    private fun locationNumber(seed: Seed): LocationNumber {
        var sourceMap = maps.getValue("seed")
        var currentNumber = seed
        while (sourceMap.destination != "location") {
            val destinationMap = maps.getValue(sourceMap.destination)
            currentNumber = nextNumber(currentNumber, sourceMap)
            sourceMap = destinationMap
        }

        return nextNumber(currentNumber, sourceMap)
    }

    private fun nextNumber(seed: Seed, map: AlmanacMap): Long {
        val entry = map.entries.firstOrNull { seed in it.sourceRange }
        return if (entry == null) {
            seed
        } else {
            val seedPosition = seed - entry.sourceRange.first
            return entry.destinationRange.first + seedPosition
        }
    }
}

class AlmanacMap(
    val source: String,
    val destination: String,
    val entries: List<Entry>,
) {

    class Entry(
        val sourceRange: LongRange,
        val destinationRange: LongRange,
    )
}

private typealias Seed = Long
private typealias LocationNumber = Long

fun main() = Day05.part1()
