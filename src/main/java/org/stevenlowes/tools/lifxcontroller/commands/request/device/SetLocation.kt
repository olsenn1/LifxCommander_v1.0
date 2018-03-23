package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayloadUpdateTime

class SetLocation(val location: ByteArray = Utils.randomBytes(16),
                  val label: String = "N/A") : RequestPayloadUpdateTime(49) {
    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(56)

            for (i in 0..15) {
                byteArray[i] = location[15 - i]
            }

            val labelBytes: ByteArray = label.toByteArray()
            for (i in 16..47) {
                byteArray[i] = labelBytes[47 - i]
            }

            val updatedAtBytes: ByteArray = Utils.toByteArray(8, updatedAtNanos.toLong())
            System.arraycopy(updatedAtBytes, 0, byteArray, 48, 8)

            return byteArray
        }
}
