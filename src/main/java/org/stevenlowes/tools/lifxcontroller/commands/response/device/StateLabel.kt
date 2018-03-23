package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

class StateLabel(var label: String = "") : ResponsePayload(25) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val labelBytes = ByteArray(32)
        System.arraycopy(byteArray, 36, labelBytes, 0, 32)
        label = String(labelBytes)
    }
}
