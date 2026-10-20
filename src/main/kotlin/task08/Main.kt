package task08
const val VAT_RATE = 0.12

// A property with a getter and no backing field can be inline as a whole.
inline val Double.withVat: Double
    get() = this * (1 + VAT_RATE)

// A property with a field cannot be inline, so the taxRate experiment in
// answers uses a const value instead.

val String.wordCount: Int
    inline get() = split(" ").count { it.isNotBlank() }   // only the getter

// Task addition: number of kilobytes.
inline val Int.kilobytes: Int
    get() = this * 1024

fun main() {
    print("Price without VAT: ")
    val price = readln().trim().toDoubleOrNull()
    if (price == null) {
        println("Not a number")
        return
    }
    println("Price with VAT: ${"%.2f".format(price.withVat)}")

    print("Write a sentence: ")
    val sentence = readln()
    println("Words: ${sentence.wordCount}")

    print("Kilobytes for a number: ")
    val kb = readln().trim().toIntOrNull() ?: 12
    println("$kb kilobytes = ${kb.kilobytes}")
}
