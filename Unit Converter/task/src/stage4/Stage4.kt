package stage4

import java.util.*
import kotlin.system.exitProcess

fun main() {

    while (true) {
        print("Enter what you want to convert (or exit): ")
        val scanner = Scanner(System.`in`)
        val firstInput = scanner.next()
        runConverter(scanner, firstInput)
    }
}

fun runConverter(scanner: Scanner, firstInput: String) {
    val number: Double
    if (firstInput == "exit") {
        exitProcess(0)
    } else {
        number = firstInput.toDouble()
    }
    val unit = scanner.next().toLowerCase()
    scanner.next()                                          // bypass the "to" or "in" keywords in the input
    val desiredUnit = scanner.next().toLowerCase()
    val numberToStandard = convertToStandard(number, unit)
    val conversion = convertToDesiredUnit(numberToStandard, desiredUnit)
    val formattedUnit = unitFormatting(unit, number)
    val formattedDesiredUnit = unitFormatting(desiredUnit, conversion)

    println("$number $formattedUnit is $conversion $formattedDesiredUnit")

}

fun unitFormatting(unit: String, number: Double): String {
    val unitName: String
    when (unit) {
        "g", "gram", "grams" -> {
            unitName = if (number == 1.0) "gram" else "grams"
        }
        "kg", "kilogram", "kilograms" -> {
            unitName = if (number == 1.0) "kilogram" else "kilograms"
        }
        "mg", "milligram", "milligrams" -> {
            unitName = if (number == 1.0) "milligram" else "milligrams"
        }
        "lb", "pound", "pounds" -> {
            unitName = if (number == 1.0) "pound" else "pounds"
        }
        "oz", "ounce", "ounces" -> {
            unitName = if (number == 1.0) "ounce" else "ounces"
        }
        "m", "meter", "meters" -> {
            unitName = if (number == 1.0) "meter" else "meters"
        }
        "km", "kilometer", "kilometers" -> {
            unitName = if (number == 1.0) "kilometer" else "kilometers"
        }
        "cm", "centimeter", "centimeters" -> {
            unitName = if (number == 1.0) "centimeter" else "centimeters"
        }
        "mm", "millimeter", "millimeters" -> {
            unitName = if (number == 1.0) "millimeter" else "millimeters"
        }
        "mi", "mile", "miles" -> {
            unitName = if (number == 1.0) "mile" else "miles"
        }
        "yd", "yard", "yards" -> {
            unitName = if (number == 1.0) "yard" else "yards"
        }
        "ft", "foot", "feet" -> {
            unitName = if (number == 1.0) "foot" else "feet"
        }
        "in", "inch", "inches" -> {
            unitName = if (number == 1.0) "inch" else "inches"
        }
        else -> throw error("Please use the correct units")
    }
    return unitName
}

fun convertToStandard(value: Double, unit: String): Double {
    val conversion: Double
    when (unit) {
        "kg", "kilogram", "kilograms" -> {
            conversion = value * 1000.0
        }
        "mg", "milligram", "milligrams" -> {
            conversion = value * 0.001
        }
        "lb", "pound", "pounds" -> {
            conversion = value * 453.592
        }
        "oz", "ounce", "ounces" -> {
            conversion = value * 28.3495
        }
        "km", "kilometer", "kilometers" -> {
            conversion = value * 1000
        }
        "cm", "centimeter", "centimeters" -> {
            conversion = value * 0.01
        }
        "mm", "millimeter", "millimeters" -> {
            conversion = value * 0.001
        }
        "mi", "mile", "miles" -> {
            conversion = value * 1609.35
        }
        "yd", "yard", "yards" -> {
            conversion = value * 0.9144
        }
        "ft", "foot", "feet" -> {
            conversion = value * 0.3048
        }
        "in", "inch", "inches" -> {
            conversion = value * 0.0254
        }
        else -> conversion = value
    }
    return conversion
}

fun convertToDesiredUnit(value: Double, unit: String): Double {
    val conversion: Double
    when (unit) {
        "kg", "kilogram", "kilograms" -> {
            conversion = value / 1000
        }
        "mg", "milligram", "milligrams" -> {
            conversion = value / 0.001
        }
        "lb", "pound", "pounds" -> {
            conversion = value / 453.592
        }
        "oz", "ounce", "ounces" -> {
            conversion = value / 28.3495
        }
        "km", "kilometer", "kilometers" -> {
            conversion = value / 1000
        }
        "cm", "centimeter", "centimeters" -> {
            conversion = value / 0.01
        }
        "mm", "millimeter", "millimeters" -> {
            conversion = value / 0.001
        }
        "mi", "mile", "miles" -> {
            conversion = value / 1609.35
        }
        "yd", "yard", "yards" -> {
            conversion = value / 0.9144
        }
        "ft", "foot", "feet" -> {
            conversion = value / 0.3048
        }
        "in", "inch", "inches" -> {
            conversion = value / 0.0254
        }
        else -> conversion = value
    }
    return conversion
}