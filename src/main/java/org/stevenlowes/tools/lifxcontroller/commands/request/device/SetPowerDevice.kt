package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Level

data class SetPowerDevice(val level: Level = Level.MIN) : RequestCommand(21) {

    override val payloadBytes: ByteArray
        get() {
            return level.byteArray
        }
}
