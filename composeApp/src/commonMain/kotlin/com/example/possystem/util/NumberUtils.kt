package com.example.possystem.util

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.format(decimals: Int = 2): String {
    val factor = 10.0.pow(decimals)
    val rounded = (this * factor).roundToInt() / factor
    
    val string = rounded.toString()
    val parts = string.split(".")
    if (parts.size == 1) {
        return "$string.${"0".repeat(decimals)}"
    }
    val decimalPart = parts[1]
    if (decimalPart.length < decimals) {
        return "$string${"0".repeat(decimals - decimalPart.length)}"
    }
    return string
}
