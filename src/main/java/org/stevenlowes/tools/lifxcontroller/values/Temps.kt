package org.stevenlowes.tools.lifxcontroller.values

/*
 * Constant values to be Assigned to Temps in HSBK Objects
 */

data class Temps(val kelvin: Int) {
    companion object {
        val WARMEST = Temps(2500)
        val MEDIUM = Temps(4000)
        val COOLEST = Temps(9000)
    }
}
