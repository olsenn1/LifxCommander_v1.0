package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateInfrared(var brightness: Int = 0) : CustomWritePayload(121) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val brightnessBinStr = Utils.convertByteToBinaryString(byteArray[36])
        brightness = Integer.parseInt(brightnessBinStr, 2)
    }
}
