fun safeReadLine(): String = readlnOrNull() ?: error("Received null value")

fun getPicture(): String {
    println("Do you want to use a predefined picture or a custom one?${newLineSymbol}Please input 'yes' for a predefined image or 'no' for a custom one")
    do {
        when (val decision = safeReadLine()) {
            "yes" -> return choosePicture()
            "no" -> {
                println("Please input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

fun choosePicture(): String {
    do {
        println("Please choose a picture. The possible options are: ${allPictures()}")
        val picture = getPictureByName(safeReadLine())
        picture?.let { return picture }
    } while (true)
}

fun chooseFilter(): String {
    println("Please choose the filter: 'borders' or 'squared'.")
    do {
        when (val input = safeReadLine()){
            "borders", "squared" -> return input
            else -> println("Please input 'borders' or 'squared'")
        }
    } while (true)
}

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

fun applySquaredFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val horizontalBorder = "$borderSymbol".repeat((pictureWidth + 4) * 2)

    val sb = StringBuilder()
    sb.append("$horizontalBorder$newLineSymbol")

    for (row in picture.lines()) {
        val singleRow = StringBuilder()

        singleRow.append("$borderSymbol$separator$row")
        if (row.length < pictureWidth) {
            singleRow.append("$separator".repeat(pictureWidth - row.length))
        }
        singleRow.append("$separator$borderSymbol")
        sb.append("${singleRow.toString().repeat(2)}$newLineSymbol")
    }

    val result = StringBuilder()
    return result.append(sb.toString().repeat(2)).append("$horizontalBorder$newLineSymbol").toString()
}

fun main() {
    // Uncomment this code on the last step of the game

    // photoshop()
}