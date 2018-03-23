package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload

class SetInfrared(var brightness: Int = 0) : CustomReadPayload(122) {
    override val byteArray: ByteArray
        get() {
            val brightnessBinStr = String.format("%16s", Integer.toBinaryString(brightness)).replace(' ', '0')
            return CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr)
        }
}
