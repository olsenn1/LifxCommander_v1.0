package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload
import org.stevenlowes.tools.lifxcontroller.values.Power

class StatePowerDevice(var level: Int = Power.OFF) : SetOnlyPayload(22) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = CommonMethods.convertByteToBinaryString(byteArray[37]) + CommonMethods.convertByteToBinaryString(
                byteArray[36])
        level = Integer.parseInt(levelBinStr, 2)
    }

}
