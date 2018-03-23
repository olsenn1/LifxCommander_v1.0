package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload
import org.stevenlowes.tools.lifxcontroller.values.Level

data class StatePowerDevice(val level: Level = Level.MIN) : ResponsePayload(22) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StatePowerDevice {
            val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                    byteArray[36])
            val level = Level(Integer.parseInt(levelBinStr, 2))

            return StatePowerDevice(level)
        }
    }
}