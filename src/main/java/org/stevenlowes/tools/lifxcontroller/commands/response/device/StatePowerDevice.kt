package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand
import org.stevenlowes.tools.lifxcontroller.values.Level

data class StatePowerDevice(val level: Level = Level.MIN) : ResponseCommand(22) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StatePowerDevice {
            val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                    byteArray[36])
            val level = Level(Integer.parseInt(levelBinStr, 2))

            return StatePowerDevice(level)
        }
    }
}