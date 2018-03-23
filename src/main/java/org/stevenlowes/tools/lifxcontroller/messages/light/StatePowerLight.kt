package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StatePowerLight(var level: Int = 0) : CustomWritePayload(118) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                byteArray[36])
        level = Integer.parseInt(levelBinStr, 2)
    }
}
