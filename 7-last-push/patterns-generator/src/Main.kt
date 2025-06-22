// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }

            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }

            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

fun getPatternHeight(pattern: String): Int = pattern.lines().size

fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    if (patternRow.length > patternWidth) {
        throw IllegalStateException("The pattern row is longer than pattern width")
    }

    return patternRow.padEnd(patternWidth, separator)
}

fun repeatHorizontally(pattern: String, n: Int, patternWidth: Int): String {
    val lines = pattern.lines()
    val result = StringBuilder()
    for (line in lines) {
        result.append(fillPatternRow(line, patternWidth).repeat(n)).append(newLineSymbol)
    }
    return result.removeSuffix(newLineSymbol).toString()
}

fun dropTopLine(image: String, width: Int, patternHeight: Int, patternWidth: Int): String {
    if (patternHeight > 1) {
        return image.drop(patternWidth * width + newLineSymbol.length)
    }
    return image
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }

            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun main() {
    // Uncomment this code on the last step of the game

    // val pattern = getPattern()
    // val generatorName = chooseGenerator()
    // println("Please input the width of the resulting picture:")
    // val width = safeReadLine().toInt()
    // println("Please input the height of the resulting picture:")
    // val height = safeReadLine().toInt()

    // println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    // println("The generated image:")
    // println(applyGenerator(pattern, generatorName, width, height))
}
