package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

class SetInfrared(var brightness: Int = 0) : GetOnlyPayload(122) {
    override val byteArray: ByteArray?
        get() {
            var byteArray: ByteArray? = ByteArray(2)

            val brightnessBinStr = String.format("%16s", Integer.toBinaryString(brightness)).replace(' ', '0')
            byteArray = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr)

            return byteArray
        }
}
