package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

class SetLabel(var label: String = "") : GetOnlyPayload(24) {

    override val byteArray: ByteArray?
        get() {
            return label.toByteArray()
        }
}
