package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads

open class DefaultPayload(code: Int): Payload(code){
    final override val byteArray = ByteArray(0)
    final override fun setFromCommandByteArray(byteArray: ByteArray) {
        /* no-op */
    }
}