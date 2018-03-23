package org.stevenlowes.tools.lifxcontroller.values

import org.stevenlowes.tools.lifxcontroller.Utils

/*
 * Constant values to be Assigned to Temp in HSBK Objects
 */

data class Temp(val kelvin: Int) {
    companion object {
        val WARMEST = Temp(2500)
        val MEDIUM = Temp(4000)
        val COOLEST = Temp(9000)
    }

    val binaryString: String = String.format("%16s", Integer.toBinaryString(kelvin)).replace(' ', '0')
    val byteArray: ByteArray = Utils.convertBinaryStringToLittleEndianByteArray(binaryString)
}
