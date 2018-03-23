package org.stevenlowes.tools.lifxcontroller.messages.datatypes

import org.stevenlowes.tools.lifxcontroller.messages.device.*
import org.stevenlowes.tools.lifxcontroller.messages.header.Frame
import org.stevenlowes.tools.lifxcontroller.messages.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.messages.header.Protocol
import org.stevenlowes.tools.lifxcontroller.messages.light.StateInfrared
import org.stevenlowes.tools.lifxcontroller.messages.light.StateLight
import org.stevenlowes.tools.lifxcontroller.messages.light.StatePowerLight

class Command(var payload: Payload) {
    val frame = Frame()
    val frameAddress = FrameAddress()
    val protocol = Protocol()

    init {
        protocol.type = payload.code
        setSize()
    }

    val byteArray: ByteArray
        get() {
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
                byteArray[i] = payload.byteArray!![i - protocolEnd]
            }

            return byteArray
        }

    private fun setSize() {
        val size: Int
        if (payload.byteArray != null)
            size = frame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size + payload.byteArray!!.size
        else
            size = frame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size
        frame.size = size
    }

    fun setFromCommandByteArray(byteArray: ByteArray) {
        frame.setFromCommandByteArray(byteArray)
        frameAddress.setFromCommandByteArray(byteArray)
        protocol.setFromCommandByteArray(byteArray)

        when (protocol.type) {
            3 -> payload = StateService()
            13 -> payload = StateHostInfo()
            15 -> payload = StateHostFirmware()
            17 -> payload = StateWifiInfo()
            19 -> payload = StateWifiFirmware()
            22 -> payload = StatePowerDevice()
            25 -> payload = StateLabel()
            33 -> payload = StateVersion()
            35 -> payload = StateInfo()
            45 -> payload = Acknowledgement()
            50 -> payload = StateLocation()
            53 -> payload = StateGroup()
            59 -> payload = EchoResponse()
            107 -> payload = StateLight()
            118 -> payload = StatePowerLight()
            121 -> payload = StateInfrared()
        }

        payload.setFromCommandByteArray(byteArray)
    }
}
