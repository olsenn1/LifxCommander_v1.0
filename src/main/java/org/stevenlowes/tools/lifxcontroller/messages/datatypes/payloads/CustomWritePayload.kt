package org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads

abstract class CustomWritePayload(code: Int) : Payload(code){
    override val byteArray = ByteArray(0)
}