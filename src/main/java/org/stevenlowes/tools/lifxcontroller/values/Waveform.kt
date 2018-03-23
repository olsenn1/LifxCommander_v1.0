package org.stevenlowes.tools.lifxcontroller.values

import org.stevenlowes.tools.lifxcontroller.Utils

enum class Waveform(val id: Int) {
    SAWTOOTH(0),
    SINUSOID(1),
    HALF_SINE(2),
    TRIANGLE(3),
    PULSE(4);

    val binaryString: String = String.format("%8s", Integer.toBinaryString(id)).replace(' ', '0')
    val byteArray: ByteArray = Utils.convertBinaryStringToLittleEndianByteArray(binaryString)
}
