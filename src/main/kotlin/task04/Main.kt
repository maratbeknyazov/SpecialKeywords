package task04
// The lambda is wrapped into a Runnable, so a non-local return could never
// reach main. crossinline promises that the lambda is only called, not escaped.
inline fun command(crossinline action: () -> Unit): Runnable =
    Runnable { action() }

fun main() {
    val commands = mapOf(
        "1" to command { println("Hello!") },
        "2" to command { println("Goodbye!") },
        "3" to command { println("Time to rest.") },
        "4" to command { println("Settings opened") },
    )
    print("Choose 1, 2, 3 or 4: ")
    val chosen = commands[readln().trim()]
    if (chosen == null) println("Unknown command") else chosen.run()
}
