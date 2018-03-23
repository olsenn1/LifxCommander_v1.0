package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

class EchoResponse(val bytes: ByteArray? = CommonMethods.randomBytes(64)) : SetOnlyPayload(59) {
    constructor(echoResponse: EchoResponse): this(echoResponse.bytes)

    override fun setFromCommandByteArray(byteArray: ByteArray) {
        for (i in 99 downTo 36) {
            //TODO why do we need this non-null ...
            bytes!![-1 * i + 99] = byteArray[i]
        }
    }

}
