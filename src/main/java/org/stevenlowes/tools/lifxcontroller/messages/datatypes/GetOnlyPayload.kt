package org.stevenlowes.tools.lifxcontroller.messages.datatypes

abstract class GetOnlyPayload(code: Int) : Payload(code) {
    final override fun setFromCommandByteArray(byteArray: ByteArray) {
        throw UnsupportedOperationException("Cannot set on a get-only payload")
    }
}