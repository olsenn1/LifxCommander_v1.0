package org.stevenlowes.tools.lifxcontroller.values

import org.stevenlowes.tools.lifxcontroller.Utils


/**
 * Used for 16-Bit values (65535 = 0xFFFF)
 */
data class Level(val value: Double) {

    constructor(value: Int): this(value.toDouble() / 65536)

    companion object {
        val MIN = Level(0.0)
        val HALF = Level(0.5)
        val MAX = Level(1.0)
    }

    val sixteenBitValue = (value * 65535).toInt()
    val binaryString: String = String.format("%16s", Integer.toBinaryString(sixteenBitValue)).replace(' ', '0')
    val byteArray: ByteArray = Utils.convertBinaryStringToLittleEndianByteArray(binaryString)
}
