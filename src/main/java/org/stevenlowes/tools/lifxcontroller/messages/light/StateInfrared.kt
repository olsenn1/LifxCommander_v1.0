package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.Payload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

class StateInfrared(var brightness: Int = 0) : SetOnlyPayload(121) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val brightnessBinStr = CommonMethods.convertByteToBinaryString(byteArray[36])
        brightness = Integer.parseInt(brightnessBinStr, 2)
    }
}