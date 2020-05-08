package stage2

import java.util.*

fun main() {
    println("Enter a number of kilometers: ")
    val scanner = Scanner(System.`in`)
    val km = scanner.nextInt()
    println("$km kilometers is ${km * 1000} meters")
}