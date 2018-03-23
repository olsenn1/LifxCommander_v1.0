package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Level

data class SetPowerLight(val level: Level = Level.MIN, var duration: Long = 0) : RequestCommand(117) {
    override val payloadBytes: ByteArray
        get() {
            val byteArray = ByteArray(6)

            val levelBytes = level.byteArray
            byteArray[0] = levelBytes[0]
            byteArray[1] = levelBytes[1]

            val durationBytes: ByteArray = Utils.toByteArray(4, duration)
            System.arraycopy(durationBytes, 0, byteArray, 2, 4)

            return byteArray
        }
}
