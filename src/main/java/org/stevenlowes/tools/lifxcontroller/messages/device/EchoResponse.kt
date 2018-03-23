package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class EchoResponse(val bytes: ByteArray = CommonMethods.randomBytes(64)) : CustomWritePayload(59) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        for (i in 99 downTo 36) {
            bytes[-1 * i + 99] = byteArray[i]
        }
    }

}
