package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

//TODO should we really be initialising with random bytes?
class EchoResponse(val bytes: ByteArray = Utils.randomBytes(64)) : ResponsePayload(59) {

    companion object {
        fun loadFrom(byteArray: ByteArray): EchoResponse {
            val bytes = ByteArray(64)
            for (i in 99 downTo 36) {
                bytes[-1 * i + 99] = byteArray[i]
            }
            return EchoResponse(bytes)
        }
    }

}
