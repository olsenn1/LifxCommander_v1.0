package org.stevenlowes.tools.lifxcontroller.commands

abstract class Payload(val code: Int) : PayloadReadAsBytes

interface PayloadReadAsBytes {
    val byteArray: ByteArray
}
