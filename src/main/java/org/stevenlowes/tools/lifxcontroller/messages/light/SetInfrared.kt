package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload
import org.stevenlowes.tools.lifxcontroller.values.Level

class SetInfrared(var brightness: Level = Level.MIN) : CustomReadPayload(122) {
    override val byteArray: ByteArray
        get() {
            return brightness.byteArray
        }
}
