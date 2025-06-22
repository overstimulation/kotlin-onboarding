fun trimPicture(picture: String): String = picture.trimIndent()

fun applyFilter(picture: String, filter: String): String {
    val trimmedPicture = trimPicture(picture)

    return when (filter) {
        "borders" -> applyBordersFilter(trimmedPicture)
        "squared" -> applySquaredFilter(trimmedPicture)
        else -> error("Unrecognised filter name")
    }
}

fun applyBordersFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val horizontalBorder = "$borderSymbol".repeat(pictureWidth + 4)

    val result = StringBuilder()
    result.append("$horizontalBorder$newLineSymbol")
    for (row in picture.lines()) {
        result.append("$borderSymbol$separator$row")
        if (row.length < pictureWidth) {
            result.append("$separator".repeat(pictureWidth - row.length))
        }
        result.append("$separator$borderSymbol$newLineSymbol")
    }
    result.append("$horizontalBorder$newLineSymbol")
    return result.toString()
}

fun applySquaredFilter(picture: String): String = TODO()

fun main() {
    // Uncomment this code on the last step of the game

    // photoshop()
}