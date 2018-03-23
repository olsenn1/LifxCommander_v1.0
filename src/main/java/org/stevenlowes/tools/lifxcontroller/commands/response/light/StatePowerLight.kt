package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand

data class StatePowerLight(val level: Int = 0) : ResponseCommand(118) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StatePowerLight{
            val levelBinStr = Utils.convertByteToBinaryString(byteArray[37]) + Utils.convertByteToBinaryString(
                    byteArray[36])
            val level = Integer.parseInt(levelBinStr, 2)
            return StatePowerLight(level)
        }
    }
}
