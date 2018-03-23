package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.UpdatedAt.CustomWriteUpdatedAtPayload

import java.math.BigInteger

class StateGroup(var group: ByteArray = Utils.randomBytes(16), var label: String = "N/A") : CustomWriteUpdatedAtPayload(53) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        for (i in 51 downTo 36) {
            group[-1 * i + 51] = byteArray[i]
        }

        val labelBytes = ByteArray(32)
        System.arraycopy(byteArray, 52, labelBytes, 0, 32)
        label = String(labelBytes)

        val updatedAtBytes = ByteArray(8)
        for (i in 91 downTo 84) {
            updatedAtBytes[-1 * i + 91] = byteArray[i]
        }

        updatedAt = BigInteger(updatedAtBytes)
    }
}
