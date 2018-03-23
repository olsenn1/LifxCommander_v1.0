package org.stevenlowes.tools.lifxcontroller.commands.simple

import org.stevenlowes.tools.lifxcontroller.commands.Payload

open class SimplePayload(code: Int): Payload(code){
    final override val byteArray = throw UnsupportedOperationException("Cannot read from default payload")
    final override fun setFromCommandByteArray(byteArray: ByteArray) {
        throw UnsupportedOperationException("Cannot write to default payload")
    }
}