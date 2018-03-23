package org.stevenlowes.tools.lifxcontroller.commands.request

import org.stevenlowes.tools.lifxcontroller.commands.HasCode
import org.stevenlowes.tools.lifxcontroller.commands.header.Frame
import org.stevenlowes.tools.lifxcontroller.commands.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol

abstract class RequestCommand(code: Int) : HasCode(code) {
    abstract val payloadBytes: ByteArray

    val requestBytes: ByteArray
        get() {
            val startFrame = Frame()
            val frameAddress = FrameAddress()
            val protocol = Protocol(type = code)

            val frame = Frame(size = startFrame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size + this.payloadBytes.size)

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
                byteArray[i] = this.payloadBytes[i - protocolEnd]
            }

            return byteArray
        }
}