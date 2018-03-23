package org.stevenlowes.tools.lifxcontroller.commands.request

import org.stevenlowes.tools.lifxcontroller.commands.Payload

abstract class RequestPayload(code: Int) : Payload(code){
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        throw UnsupportedOperationException("Cannot set a request payload")
    }
}