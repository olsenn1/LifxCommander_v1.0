package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateInfrared(var brightness: Int = 0) : CustomWritePayload(121) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val brightnessBinStr = CommonMethods.convertByteToBinaryString(byteArray[36])
        brightness = Integer.parseInt(brightnessBinStr, 2)
    }
}
