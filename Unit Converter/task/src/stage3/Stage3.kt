package stage3

import java.util.*

fun main() {
    println("Enter a number and a measure of length: ")
    val scanner = Scanner(System.`in`)
    val number = scanner.nextDouble()
    val unit = scanner.next().toLowerCase()
    val unitName: String
    val conversion: Double
    val convertedName: String

    when (unit) {
        "m", "meter", "meters" -> {
            unitName = if (number == 1.0) "meter" else "meters"
            conversion = number * 1.0
        }
        "km", "kilometer", "kilometers" -> {
            unitName = if (number == 1.0) "kilometer" else "kilometers"
            conversion = number * 1000
        }
        "cm", "centimeter", "centimeters" -> {
            unitName = if (number == 1.0) "centimeter" else "centimeters"
            conversion = (number * 0.01)
        }
        "mm", "millimeter", "millimeters" -> {
            unitName = if (number == 1.0) "millimeter" else "millimeters"
            conversion = (number * 0.001)
        }
        "mi", "mile", "miles" -> {
            unitName = if (number == 1.0) "mile" else "miles"
            conversion = (number * 1609.35)
        }
        "yd", "yard", "yards" -> {
            unitName = if (number == 1.0) "yard" else "yards"
            conversion = (number * 0.9144)
        }
        "ft", "foot", "feet" -> {
            unitName = if (number == 1.0) "foot" else "feet"
            conversion = (number * 0.3048)
        }
        "in", "inch", "inches" -> {
            unitName = if (number == 1.0) "inch" else "inches"
            conversion = (number * 0.0254)
        }
        else -> throw error("Please use the correct units")
    }

    convertedName = if (conversion == 1.0) {
        "meter"
    } else {
        "meters"
    }

    println("$number $unitName is $conversion $convertedName")
}