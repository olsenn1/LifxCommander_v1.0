package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads

abstract class CustomReadPayload(code: Int) : Payload(code){
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        /* no-op */
    }
}