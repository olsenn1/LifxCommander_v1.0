package org.stevenlowes.tools.lifxcontroller.values

/*
 * Constant values to be Assigned to Hue in HSBK Objects
 */

data class Hue(val hueDegrees: Int) {

    companion object {
        val RED = Hue(0)
        val ORANGE = Hue(30)
        val YELLOW = Hue(60)
        val LIME = Hue(90)
        val GREEN = Hue(120)
        val TURQUOISE = Hue(150)
        val CYAN = Hue(180)
        val COBALT = Hue(210)
        val BLUE = Hue(240)
        val VIOLET = Hue(270)
        val MAGENTA = Hue(300)
        val CRIMSON = Hue(330)
    }

    val binaryString: String get() = Integer.toBinaryString((hueDegrees.toDouble() / 360.toDouble() * Short.MAX_VALUE).toInt())
}
