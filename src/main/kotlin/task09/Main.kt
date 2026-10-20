package task09
// The internal flag is used inside a public inline function, so it must be
// published for the copied bodies: @PublishedApi.
@PublishedApi
internal var debugEnabled = false

// inline defers message construction: the lambda body runs only when it is used.
inline fun debug(message: () -> String) {
    if (debugEnabled) println("[DEBUG] ${message()}")
}

// Task addition: a normal (not lazy) debug for comparison.
fun debugPlain(message: String) {
    if (debugEnabled) println("[DEBUG] $message")
}

fun buildReport(): String {
    println("(building the report...)")
    return "report with 1000 rows"
}

fun main() {
    print("Enable debug logging? (yes/no): ")
    debugEnabled = readln().trim().equals("yes", ignoreCase = true)
    debug { buildReport() }
    println("Program finished")
}
