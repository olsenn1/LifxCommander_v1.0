package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

class StateInfrared(var brightness: Int = 0) : ResponsePayload(121) {
    companion object {
        fun loadFrom(byteArray: ByteArray): StateInfrared{
            val brightnessBinStr = Utils.convertByteToBinaryString(byteArray[36])
            val brightness = Integer.parseInt(brightnessBinStr, 2)
            return StateInfrared(brightness)
        }
    }
}
