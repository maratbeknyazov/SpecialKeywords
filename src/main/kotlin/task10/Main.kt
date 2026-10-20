package task10
typealias Gradebook = MutableMap<String, Grades>
typealias Grades = MutableList<Int>

fun Gradebook.addGrade(student: String, grade: Int) {
    getOrPut(student) { mutableListOf() }.add(grade)
}

fun Grades.best(): Int? = maxOrNull()

fun main() {
    val book: Gradebook = mutableMapOf()
    println("Enter lines like: Ali 85   (type 'end' to stop)")
    while (true) {
        val line = readln().trim()
        if (line == "end") break
        val parts = line.split(" ")
        val grade = parts.getOrNull(1)?.toIntOrNull()
        if (parts.size != 2 || grade == null) {
            println("Use: name grade")
            continue
        }
        book.addGrade(parts[0], grade)
    }
    for ((name, grades) in book) {
        val avg = "%.1f".format(grades.average())
        println("$name: ${grades.joinToString()}  average = $avg  best = ${grades.best()}")
    }
}
