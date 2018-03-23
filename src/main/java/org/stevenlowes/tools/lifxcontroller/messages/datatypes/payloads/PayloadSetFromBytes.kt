package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads

interface PayloadSetFromBytes {
    fun setFromCommandByteArray(byteArray: ByteArray)
}