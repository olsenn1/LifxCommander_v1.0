package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload

class SetLabel(var label: String = "") : RequestPayload(24) {
    override val byteArray: ByteArray
        get() {
            return label.toByteArray()
        }
}