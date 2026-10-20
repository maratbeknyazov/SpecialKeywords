package task03
val contacts = listOf("Ali Karimov", "Dilnoza Yusupova", "Aliyah Smith", "Bobur Aliev")

fun findFirst(query: String) {
    contacts.forEach {
        if (it.contains(query, ignoreCase = true)) {
            println("First match: $it")
            return   // non-local return: leaves the whole function
        }
    }
    println("No contact contains '$query'")
}

fun findAll(query: String) {
    contacts.forEach {
        if (!it.contains(query, ignoreCase = true)) return@forEach  // like continue
        println("Match: $it")
    }
}

// The lesson: bare return inside a normal (not inline) lambda does not compile.
inline fun each(items: List<String>, action: (String) -> Unit) {
    for (item in items) action(item)
}

fun findWithEach(query: String) {
    each(contacts) {
        if (it.contains(query, ignoreCase = true)) {
            println("First match: $it")
            return
        }
    }
}

fun main() {
    print("Search: ")
    val query = readln().trim()
    findFirst(query)
    findAll(query)
    findWithEach(query)
    println("Search finished")
}
