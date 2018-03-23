package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateLabel(var label: String = "") : CustomWritePayload(25) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val labelBytes = ByteArray(32)
        System.arraycopy(byteArray, 36, labelBytes, 0, 32)
        label = String(labelBytes)
    }
}
