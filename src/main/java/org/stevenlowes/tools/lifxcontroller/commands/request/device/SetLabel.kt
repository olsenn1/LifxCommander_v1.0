package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload

data class SetLabel(val label: String = "") : RequestPayload(24) {
    override val byteArray: ByteArray
        get() {
            return label.toByteArray()
        }
}
