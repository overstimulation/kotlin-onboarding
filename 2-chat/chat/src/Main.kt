fun main() {
    println("Hello! I'm glad to meet you, let me get to know you better! What is your name?")
    val firstUserAnswer: String? = readlnOrNull()

    println("Nice to meet you, $firstUserAnswer! My name is Kotlin Bot! I am a young programming language created in 2010. How old are you?")
    val secondUserAnswer: String? = readlnOrNull()
}