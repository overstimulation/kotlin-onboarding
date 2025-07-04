fun getGameRules(wordLength: Int, maxAttemptsCount: Int) = "Welcome to the game!$newLineSymbol$newLineSymbol" +
        "In this game, you need to guess the word made by the computer.$newLineSymbol" +
        "The hidden word will appear as a sequence of underscores, one underscore means one letter.$newLineSymbol" +
        "You have $maxAttemptsCount attempts to guess the word.$newLineSymbol" +
        "All words are English words, consisting of $wordLength letters.$newLineSymbol" +
        "Each attempt you should enter any one letter,$newLineSymbol" +
        "if it is in the hidden word, all matches will be guessed.$newLineSymbol$newLineSymbol" +
        "" +
        "For example, if the word \"CAT\" was guessed, \"_ _ _\" will be displayed first, " +
        "since the word has 3 letters.$newLineSymbol" +
        "If you enter the letter A, you will see \"_ A _\" and so on.$newLineSymbol$newLineSymbol" +
        "" +
        "Good luck in the game!"

fun generateSecret(): String = words.random()

fun getHiddenSecret(wordLength: Int): String = List(wordLength) { underscore }.joinToString(separator)

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    var newUserWord: String = ""

    for (i in secret.indices) {
        newUserWord += if (secret[i] == guess) {
            "${secret[i]}$separator"
        } else {
            "${currentUserWord[i * 2]}$separator"
        }
    }

    return newUserWord.removeSuffix(separator)
}

fun isCorrectInput(userInput: String): Boolean {
    if (userInput.length != 1) {
        println("The length of your guess should be 1! Try again!")
        return false
    }

    if (!userInput[0].isLetter()) {
        println("You should input only English letters! Try again!")
        return false
    }

    return true
}

fun safeUserInput(): Char {
    var guess: String

    do {
        println("Please input your guess.")
        guess = safeReadLine()
    } while (!isCorrectInput(guess))

    return guess.uppercase()[0]
}

fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String {
    if (guess !in secret) {
        println("Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord")
        return currentUserWord
    } else {
        val newUserWord = generateNewUserWord(secret, guess, currentUserWord)
        println("Great! This letter is in the word! The current word is $newUserWord")
        return newUserWord
    }
}

fun isComplete(secret: String, currentGuess: String): Boolean = secret == currentGuess.replace(separator, "")

fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun playGame(secret: String, maxAttemptsCount: Int) {
    var attempts: Int = 0
    var complete: Boolean
    var currentGuess: String = getHiddenSecret(wordLength)

    println("I guessed a word: $currentGuess")
    do {
        val guess = safeUserInput()
        currentGuess = getRoundResults(secret, guess, currentGuess)
        attempts++

        complete = isComplete(secret, currentGuess)

        if (isWon(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
            break
        }

        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! My word is $secret")
            break
        }

    } while (!complete)
}

fun main() {
    println(getGameRules(wordLength, maxAttemptsCount))
    playGame(generateSecret(), maxAttemptsCount)
}
