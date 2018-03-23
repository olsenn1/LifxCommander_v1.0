package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload

class EchoRequest(var payload: ByteArray = Utils.randomBytes(64)) : RequestPayload(58) {
    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(64)
            for (i in 0..63) {
                byteArray[i] = payload[-1 * i + 63]
            }
            return byteArray
        }
}
