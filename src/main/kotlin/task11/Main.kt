package task11
typealias Validator = (String) -> Boolean
typealias Predicate<T> = (T) -> Boolean

val notBlank: Validator = { it.isNotBlank() }
val isEmail: Validator = { '@' in it && '.' in it.substringAfter('@') }
val isStrongPassword: Validator = { it.length >= 8 && it.any(Char::isDigit) }

fun check(label: String, input: String, validator: Validator) {
    println("$label: ${if (validator(input)) "OK" else "INVALID"}")
}

fun <T> countMatching(items: List<T>, rule: Predicate<T>): Int = items.count(rule)

// Add: rules based on Predicate<String>.
typealias Rule = Predicate<String>

val startsWithCode: Rule = { it.startsWith("+998") }

// Add: the room demo with Meters and Feet.
typealias Meters = Double
typealias Feet = Double

fun toFeet(length: Meters): Feet = length * 3.28084

fun describeRoom(width: Meters) {
    println("Room width: $width m")
}

fun roomDemo() {
    print("Room width in meters: ")
    val width: Meters = readln().trim().toDouble()
    val feet: Feet = toFeet(width)
    println("That is $feet feet")
    describeRoom(feet)   // accepts feet: alias does not create a new type
}

fun main() {
    print("E-mail: ")
    val email = readln().trim()
    print("Password: ")
    val password = readln()
    check("E-mail", email, isEmail)
    check("Password", password, isStrongPassword)
    check("Not blank", email) { it.isNotBlank() }   // a plain lambda for Validator
    val plain: (String) -> Boolean = isEmail        // a Validator as a plain type
    println("Used as a plain function type: ${plain(email)}")
    println("Valid e-mails in the list: ${countMatching(listOf(email, "x@y.uz", "bad"), isEmail)}")

    print("Number 1 to check the +998 code: ")
    val p1 = readln().trim()
    print("Number 2: ")
    val p2 = readln().trim()
    print("Number 3: ")
    val p3 = readln().trim()
    println("With the +998 code: ${countMatching(listOf(p1, p2, p3), startsWithCode)}")

    roomDemo()
}
