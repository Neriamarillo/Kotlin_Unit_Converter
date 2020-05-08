package stage5

import java.util.*

enum class MeasurementGroups {
    Length, Weight, Temperature, Unknown;
}

enum class MeasurementUnits {
    METER, KILOMETER, MILLIMETER, CENTIMETER, MILE, YARD, FOOT, INCH,
    GRAM, KILOGRAM, MILLIGRAM, POUND, OUNCE,
    CELSIUS, FAHRENHEIT, KELVINS,
    UNKNOWN;

    fun getGroup() = when (this) {
        METER, KILOMETER, MILLIMETER, CENTIMETER, MILE, YARD, FOOT, INCH -> MeasurementGroups.Length
        GRAM, KILOGRAM, MILLIGRAM, POUND, OUNCE -> MeasurementGroups.Weight
        CELSIUS, FAHRENHEIT, KELVINS -> MeasurementGroups.Temperature
        else -> MeasurementGroups.Unknown
    }

    fun getProperUnitName(value: Double) = when (this) {
        METER -> if (value == 1.0) "meter" else "meters"
        KILOMETER -> if (value == 1.0) "kilometer" else "kilometers"
        CENTIMETER -> if (value == 1.0) "centimeter" else "centimeters"
        MILLIMETER -> if (value == 1.0) "millimeter" else "millimeters"
        MILE -> if (value == 1.0) "mile" else "miles"
        YARD -> if (value == 1.0) "yard" else "yards"
        FOOT -> if (value == 1.0) "foot" else "feet"
        INCH -> if (value == 1.0) "inch" else "inches"
        GRAM -> if (value == 1.0) "gram" else "grams"
        KILOGRAM -> if (value == 1.0) "kilogram" else "kilograms"
        MILLIGRAM -> if (value == 1.0) "milligram" else "milligrams"
        POUND -> if (value == 1.0) "pound" else "pounds"
        OUNCE -> if (value == 1.0) "ounce" else "ounces"
        CELSIUS -> if (value == 1.0) "degree Celsius" else "degrees Celsius"
        FAHRENHEIT -> if (value == 1.0) "degree Fahrenheit" else "degrees Fahrenheit"
        KELVINS -> if (value == 1.0) "Kelvin" else "Kelvins"
        else -> "???"
    }

    fun getConversionValues() = when (this) {
        METER -> 1.0
        KILOMETER -> 1000.0
        CENTIMETER -> 0.01
        MILLIMETER -> 0.001
        MILE -> 1689.35
        YARD -> 0.9144
        FOOT -> 0.3048
        INCH -> 0.0254
        GRAM -> 1.0
        KILOGRAM -> 1000.0
        MILLIGRAM -> 0.001
        POUND -> 453.592
        OUNCE -> 28.3495
        else -> 0.0
    }
}

fun getUnit(scanner: Scanner): MeasurementUnits {

    var unit = scanner.next()
    if (unit.toLowerCase() == "degree" || unit.toLowerCase() == "degrees") {
        unit += " " + scanner.next()
    }

    return when (unit.toLowerCase()) {
        "m", "meter", "meters" -> MeasurementUnits.METER
        "km", "kilometer", "kilometers" -> MeasurementUnits.KILOMETER
        "cm", "centimeter", "centimeters" -> MeasurementUnits.CENTIMETER
        "mm", "millimeter", "millimeters" -> MeasurementUnits.MILLIMETER
        "mi", "mile", "miles" -> MeasurementUnits.MILE
        "yd", "yard", "yards" -> MeasurementUnits.YARD
        "ft", "foot", "feet" -> MeasurementUnits.FOOT
        "in", "inch", "inches" -> MeasurementUnits.INCH
        "g", "gram", "grams" -> MeasurementUnits.GRAM
        "kg", "kilogram", "kilograms" -> MeasurementUnits.KILOGRAM
        "mg", "milligram", "milligrams" -> MeasurementUnits.MILLIGRAM
        "lb", "pound", "pounds" -> MeasurementUnits.POUND
        "oz", "ounce", "ounces" -> MeasurementUnits.OUNCE
        "degree celsius", "degrees celsius", "celsius", "dc", "c" -> MeasurementUnits.CELSIUS
        "degree fahrenheit", "degrees fahrenheit", "fahrenheit", "df", "f" -> MeasurementUnits.FAHRENHEIT
        "kelvin", "kelvins", "k" -> MeasurementUnits.KELVINS
        else -> MeasurementUnits.UNKNOWN
    }
}

fun convert(value: Double, unitFrom: MeasurementUnits, unitTo: MeasurementUnits): Double {
    return if (unitFrom == unitTo) {
        value
    } else when (Pair(unitFrom, unitTo)) {
        Pair(MeasurementUnits.FAHRENHEIT, MeasurementUnits.CELSIUS) -> (value - 32) * 5 / 9
        Pair(MeasurementUnits.CELSIUS, MeasurementUnits.FAHRENHEIT) -> value * 9 / 5 + 32
        Pair(MeasurementUnits.CELSIUS, MeasurementUnits.KELVINS) -> value + 273.15
        Pair(MeasurementUnits.KELVINS, MeasurementUnits.CELSIUS) -> value - 273.15
        Pair(MeasurementUnits.KELVINS, MeasurementUnits.FAHRENHEIT) -> value * 9 / 5 - 459.67
        Pair(MeasurementUnits.FAHRENHEIT, MeasurementUnits.KELVINS) -> (value + 459.67) * 5 / 9
        else -> value * unitFrom.getConversionValues() / unitTo.getConversionValues()
    }
}

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)

    while (true) {
        println("Enter what you want to convert (or exit): ")

        val isNum = scanner.hasNextDouble()
        val inputValue = scanner.next()
        if (inputValue == "exit") {
            break
        } else if (!isNum) {
            scanner.nextLine()
            println("Parse error")
        } else {
            val value = inputValue.toDouble()
            val convertFrom = getUnit(scanner)
            scanner.next()                                              // bypass the "to" or "in" keywords in the input
            val convertTo = getUnit(scanner)

            if (convertTo.getGroup() != convertFrom.getGroup() || convertTo == MeasurementUnits.UNKNOWN) {
                println("Conversion from ${convertFrom.getProperUnitName(1.5)}" +
                        " to ${convertTo.getProperUnitName(1.5)} is impossible")
                continue
            }

            if (convertFrom.getGroup() != MeasurementGroups.Temperature && value < 0) {
                println("${convertFrom.getGroup()} shouldn't be negative")
                continue
            }

            val conversionResult = convert(value, convertFrom, convertTo)
            println("$value ${convertFrom.getProperUnitName(value)} " +
                    "is $conversionResult ${convertTo.getProperUnitName(conversionResult)}")
        }
    }
}