package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomReadPayload

class SetLabel(var label: String = "") : CustomReadPayload(24) {
    override val byteArray: ByteArray
        get() {
            return label.toByteArray()
        }
}
