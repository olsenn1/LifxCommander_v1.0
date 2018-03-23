package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand

data class EchoRequest(val payload: ByteArray = Utils.randomBytes(64)) : RequestCommand(58) {
    override val payloadBytes: ByteArray
        get() {
            val byteArray = ByteArray(64)
            for (i in 0..63) {
                byteArray[i] = payload[-1 * i + 63]
            }
            return byteArray
        }
}
