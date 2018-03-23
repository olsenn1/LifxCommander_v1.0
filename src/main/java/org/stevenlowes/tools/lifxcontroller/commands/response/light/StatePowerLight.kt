package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

class StatePowerLight(var level: Int = 0) : ResponsePayload(118) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                byteArray[36])
        level = Integer.parseInt(levelBinStr, 2)
    }
}
