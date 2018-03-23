package org.stevenlowes.tools.lifxcontroller.commands.request

import org.stevenlowes.tools.lifxcontroller.commands.Payload
import org.stevenlowes.tools.lifxcontroller.commands.header.Frame
import org.stevenlowes.tools.lifxcontroller.commands.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol

abstract class RequestPayload(code: Int) : Payload(code) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        throw UnsupportedOperationException("Cannot set a request payload")
    }

    val commandByteArray: ByteArray
        get() {
            val frame = Frame()
            val frameAddress = FrameAddress()
            val protocol = Protocol(type = code)
            frame.size = frame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size + this.byteArray.size

            val byteArray = ByteArray(frame.size)
            val frameEnd = frame.byteArray.size
            val frameAddressEnd = frame.byteArray.size + frameAddress.byteArray.size
            val protocolEnd = frame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size

            for (i in 0 until frameEnd) {
                byteArray[i] = frame.byteArray[i]
            }

            for (i in frameEnd until frameAddressEnd) {
                byteArray[i] = frameAddress.byteArray[i - frameEnd]
            }

            for (i in frameAddressEnd until protocolEnd) {
                byteArray[i] = protocol.byteArray[i - frameAddressEnd]
            }

            for (i in protocolEnd until frame.size) {
                byteArray[i] = this.byteArray[i - protocolEnd]
            }

            return byteArray
        }
}