package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class SetPowerDevice(val level: Level = Level.MIN) : RequestPayload(21) {

    override val byteArray: ByteArray
        get() {
            return level.byteArray
        }
}
