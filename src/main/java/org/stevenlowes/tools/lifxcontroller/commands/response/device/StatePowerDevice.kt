package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class StatePowerDevice(var level: Level = Level.MIN) : ResponsePayload(22) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                byteArray[36])
        level = Level(Integer.parseInt(levelBinStr, 2))
    }
}
