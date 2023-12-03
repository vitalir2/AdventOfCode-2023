/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

typealias Input = List<String>

data class Size(
    val width: Int,
    val height: Int,
)
