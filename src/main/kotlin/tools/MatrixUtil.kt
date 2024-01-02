package tools

enum class Directions {
    UP, DOWN, RIGHT, LEFT
}

fun <T> List<List<T>>.rotate90right(): List<List<T>> {
    val rotated = MutableList<MutableList<T>>(this[0].size) { mutableListOf() }
    (0.until(this[0].size)).forEach { column ->
        this.indices.forEach {
            rotated[column].add(this[this.size - 1 - it][column])
        }
    }
    return rotated
}