package task05
// inline lets the lambda be copied, so "continue" inside it is a non-local
// continue that leaves the while loop of the caller (Kotlin 2.2+).
inline fun orSkip(text: String?, onNull: () -> Double): Double =
    text?.toDoubleOrNull() ?: onNull()

fun main() {
    val prices = mutableListOf<Double>()
    println("Enter prices, one per line. Type 'done' to finish.")
    while (true) {
        val line = readln().trim()
        if (line == "done") break

        val price = orSkip(line) {
            println("'$line' is not a number, skipped")
            continue   // non-local continue: skips this round of the while loop
        }

        price.takeIf { it > 10_000 }?.let {
            println("Price $it is too large, stopping")
            break      // non-local break: stops the while loop
        }
        prices.add(price)
    }
    println("Items: ${prices.size}, total: ${prices.sum()}")
}
