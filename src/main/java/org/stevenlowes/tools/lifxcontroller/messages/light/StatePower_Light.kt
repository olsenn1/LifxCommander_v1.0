package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.Payload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

class StatePower_Light(var level: Int = 0) : SetOnlyPayload(118) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = CommonMethods.convertByteToBinaryString(byteArray[37]) + CommonMethods.convertByteToBinaryString(
                byteArray[36])
        level = Integer.parseInt(levelBinStr, 2)
    }
}
