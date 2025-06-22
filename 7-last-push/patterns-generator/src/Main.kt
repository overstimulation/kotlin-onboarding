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

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val result = StringBuilder()
    val horizontalLine: String = repeatHorizontally(pattern, width, pattern.lines().maxOfOrNull { it.length } ?: 0)
    val horizontalLineDrop: String = dropTopLine(
        horizontalLine,
        width,
        getPatternHeight(horizontalLine),
        pattern.lines().maxOfOrNull { it.length } ?: 0)

    result.append(horizontalLine, newLineSymbol)

    for (i in 1 until height) {
        if (getPatternHeight(pattern) > 1) result.append(horizontalLineDrop, newLineSymbol)
        else result.append(horizontalLine, newLineSymbol)
    }

    return result.removeSuffix(newLineSymbol).toString()
}

fun repeatHorizontallyWithGaps(pattern: String, n: Int, startWithPattern: Boolean): String {
    val patternLines = pattern.lines()
    val patternWidth = getPatternWidth(pattern)
    val gapLine = " ".repeat(patternWidth)
    val result = StringBuilder()

    for (i in patternLines.indices) {
        val currentRow = fillPatternRow(patternLines[i], patternWidth)
        val currentLineBuilder = StringBuilder()

        for (j in 0 until n) {
            if (startWithPattern) {
                if (j % 2 == 0) {
                    currentLineBuilder.append(currentRow)
                } else {
                    currentLineBuilder.append(gapLine)
                }
            } else {
                if (j % 2 == 0) {
                    currentLineBuilder.append(gapLine)
                } else {
                    currentLineBuilder.append(currentRow)
                }
            }
        }
        result.append(currentLineBuilder.toString())
        result.append(newLineSymbol)
    }
    return result.removeSuffix(newLineSymbol).toString()
}

fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    if (height < 1) {
        return ""
    }

    val lineStartingWithPattern = repeatHorizontallyWithGaps(pattern, width, true)
    val lineStartingWithGap = repeatHorizontallyWithGaps(pattern, width, false)
    val resultBuilder = StringBuilder()

    for (i in 0 until height) {
        if (i % 2 == 0) {
            resultBuilder.append(lineStartingWithPattern)
        } else {
            resultBuilder.append(lineStartingWithGap)
        }
        if (i < height - 1) {
            resultBuilder.append(newLineSymbol)
        }
    }
    return resultBuilder.toString()
}

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

fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    val trimmedPattern = pattern.trimIndent()
    return when (generatorName) {
        "canvas" -> canvasGenerator(trimmedPattern, width, height)
        "canvasGaps" -> canvasWithGapsGenerator(trimmedPattern, width, height)
        else -> throw IllegalStateException("Unexpected generator name: $generatorName")
    }
}

fun main() {
    val pattern = getPattern()
    val generatorName = chooseGenerator()
    println("Please input the width of the resulting picture:")
    val width = safeReadLine().toInt()
    println("Please input the height of the resulting picture:")
    val height = safeReadLine().toInt()

    println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    println("The generated image:")
    println(applyGenerator(pattern, generatorName, width, height))
}

