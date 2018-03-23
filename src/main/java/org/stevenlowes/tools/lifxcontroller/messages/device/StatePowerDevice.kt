package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class StatePowerDevice(var level: Level = Level.MIN) : CustomWritePayload(22) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                byteArray[36])
        level = Level(Integer.parseInt(levelBinStr, 2))
    }
}
