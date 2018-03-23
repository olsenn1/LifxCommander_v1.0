package org.stevenlowes.tools.lifxcontroller.commands.request

open class SimpleRequestCommand(code: Int): RequestCommand(code){
    override val payloadBytes: ByteArray = ByteArray(0)
}