package org.stevenlowes.tools.lifxcontroller.commands

abstract class Payload(val code: Int) : PayloadReadAsBytes, PayloadSetFromBytes

interface PayloadReadAsBytes {
    val byteArray: ByteArray
}

interface PayloadSetFromBytes {
    fun setFromCommandByteArray(byteArray: ByteArray)
}
