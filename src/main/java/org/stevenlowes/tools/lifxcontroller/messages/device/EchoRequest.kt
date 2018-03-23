package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload

class EchoRequest(payload: ByteArray? = CommonMethods.randomBytes(64)) : GetOnlyPayload(58) {
    var payload: ByteArray? = null

    override val byteArray: ByteArray?
        get() {
            val byteArray = ByteArray(64)
            for (i in 0..63) {
                byteArray[i] = payload!![-1 * i + 63]
            }
            return byteArray
        }

    constructor(echoRequest: EchoRequest): this(echoRequest.payload)
}
