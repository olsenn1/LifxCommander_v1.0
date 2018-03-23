package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class SetPowerDevice(var level: Level = Level.MIN) : CustomReadPayload(21) {

    override val byteArray: ByteArray
        get() {
            return level.byteArray
        }
}
