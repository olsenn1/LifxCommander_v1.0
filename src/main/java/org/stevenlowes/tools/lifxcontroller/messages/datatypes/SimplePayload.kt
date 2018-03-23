package org.stevenlowes.tools.lifxcontroller.messages.datatypes

open class SimplePayload(code: Int): Payload(code){
    final override val byteArray: ByteArray? = null //TODO can we make this a non-null parameter?
    final override fun setFromCommandByteArray(byteArray: ByteArray) {/* no-op */}
}