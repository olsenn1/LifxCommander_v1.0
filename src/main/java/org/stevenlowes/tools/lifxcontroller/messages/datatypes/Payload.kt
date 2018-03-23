package org.stevenlowes.tools.lifxcontroller.messages.datatypes

abstract class Payload(val code: Int) {
    abstract val byteArray: ByteArray?
    abstract fun setFromCommandByteArray(byteArray: ByteArray)

}
