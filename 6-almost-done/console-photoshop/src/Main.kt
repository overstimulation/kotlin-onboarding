fun trimPicture(picture: String): String = picture.trimIndent()

fun applyFilter(picture: String, filter: String): String {
    val trimmedPicture = trimPicture(picture)

    return when (filter) {
        "borders" -> applyBordersFilter(trimmedPicture)
        "squared" -> applySquaredFilter(trimmedPicture)
        else -> error("Unrecognised filter name")
    }
}

fun applyBordersFilter(picture: String): String = TODO()

fun applySquaredFilter(picture: String): String = TODO()

fun main() {
    // Uncomment this code on the last step of the game

    // photoshop()
}