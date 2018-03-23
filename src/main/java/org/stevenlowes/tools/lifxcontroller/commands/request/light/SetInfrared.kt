package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class SetInfrared(var brightness: Level = Level.MIN) : RequestPayload(122) {
    override val byteArray: ByteArray
        get() {
            return brightness.byteArray
        }
}
