fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun isCorrectInput(userInput: String, wordLength: Int, alphabet: String): Boolean {
    if (userInput.length != wordLength) {
        println("The length of your guess should be $wordLength characters! Try again!")
        return false
    }

    if (userInput.any { it !in alphabet }) {
        return false
    }

    return true
}

fun safeUserInput(wordLength: Int, alphabet: String): String {
    var userInput: String

    do {
        println("Please input your guess. It should be of length $wordLength, and each symbol should be from the alphabet: $alphabet.")
        userInput = safeReadLine()
    } while (!isCorrectInput(userInput, wordLength, alphabet))

    return userInput
}

fun generateSecret(wordLength: Int, alphabet: String): String = List(wordLength) { alphabet.random() }.joinToString("")

fun countAllMatches(secret: String, guess: String): Int = minOf(guess.filter { it in secret }.length, secret.filter { it in guess }.length)

fun countPartialMatches(secret: String, guess: String): Int = countAllMatches(secret, guess) - countExactMatches(secret, guess)

fun countExactMatches(secret: String, guess: String): Int = secret.filterIndexed { index, symbol -> guess[index] == symbol }.length

fun printRoundResults(secret: String, guess: String) = println("Your guess has ${countExactMatches(secret, guess)} full matches and ${countPartialMatches(secret, guess)} partial matches.")

fun isComplete(secret: String, guess: String): Boolean = secret == guess

fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean = !complete && attempts > maxAttemptsCount

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var attempts: Int = 0
    var complete: Boolean

    do {
        println("Please input your guess. It should be of length $wordLength.")
        val guess = safeReadLine()
        attempts++
        printRoundResults(secret, guess)

        complete = isComplete(secret, guess)

        if (isWon(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
            break
        } else if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! :( My word is $secret")
            break
        }
    } while (!complete)
}

fun main() {
    val wordLength: Int = 4
    val maxAttemptsCount: Int = 3
    val secretExample: String = "ACEB"
    val alphabet = "ABCDEFGH"

    println(getGameRules(wordLength, maxAttemptsCount, secretExample))
    playGame(generateSecret(wordLength, alphabet), wordLength, maxAttemptsCount)
}
