package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

class StateInfrared(var brightness: Int = 0) : ResponsePayload(121) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val brightnessBinStr = Utils.convertByteToBinaryString(byteArray[36])
        brightness = Integer.parseInt(brightnessBinStr, 2)
    }
}
