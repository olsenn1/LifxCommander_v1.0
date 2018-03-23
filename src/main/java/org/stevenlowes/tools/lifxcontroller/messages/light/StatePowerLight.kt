package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StatePowerLight(var level: Int = 0) : CustomWritePayload(118) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = CommonMethods.convertByteToBinaryString(byteArray[37]) + CommonMethods.convertByteToBinaryString(
                byteArray[36])
        level = Integer.parseInt(levelBinStr, 2)
    }
}
