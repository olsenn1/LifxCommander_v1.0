package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand

data class SetLabel(val label: String = "") : RequestCommand(24) {
    override val payloadBytes: ByteArray
        get() {
            return label.toByteArray()
        }
}
