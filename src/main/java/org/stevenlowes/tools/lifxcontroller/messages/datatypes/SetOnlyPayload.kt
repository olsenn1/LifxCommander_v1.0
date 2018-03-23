package org.stevenlowes.tools.lifxcontroller.messages.datatypes

abstract class SetOnlyPayload(code: Int): Payload(code){
    final override val byteArray: ByteArray?
        get() = throw UnsupportedOperationException("Cannot get from a set-only payload")
}