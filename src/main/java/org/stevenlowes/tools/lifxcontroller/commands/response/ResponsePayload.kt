package org.stevenlowes.tools.lifxcontroller.commands.response

import org.stevenlowes.tools.lifxcontroller.commands.Payload
import org.stevenlowes.tools.lifxcontroller.commands.header.Frame
import org.stevenlowes.tools.lifxcontroller.commands.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol
import org.stevenlowes.tools.lifxcontroller.commands.response.device.*
import org.stevenlowes.tools.lifxcontroller.commands.response.light.StateInfrared
import org.stevenlowes.tools.lifxcontroller.commands.response.light.StateLight
import org.stevenlowes.tools.lifxcontroller.commands.response.light.StatePowerLight
import org.stevenlowes.tools.lifxcontroller.commands.simple.device.Acknowledgement

//TODO what is the difference between this and SimplePayload

abstract class ResponsePayload(code: Int) : Payload(code){
    override val byteArray = throw UnsupportedOperationException("Cannot read a Response Payload")

    companion object {
        fun loadFrom(byteArray: ByteArray): ResponsePayload{
            val frame = Frame.loadFrom(byteArray)
            val frameAddress = FrameAddress.loadFrom(byteArray)
            val protocol = Protocol.loadFrom(byteArray)

            val payload: ResponsePayload

            when (protocol.type) {
                3 -> payload = StateService.loadFrom(byteArray)
                13 -> payload = StateHostInfo.loadFrom(byteArray)
                15 -> payload = StateHostFirmware.loadFrom(byteArray)
                17 -> payload = StateWifiInfo.loadFrom(byteArray)
                19 -> payload = StateWifiFirmware.loadFrom(byteArray)
                22 -> payload = StatePowerDevice.loadFrom(byteArray)
                25 -> payload = StateLabel.loadFrom(byteArray)
                33 -> payload = StateVersion.loadFrom(byteArray)
                35 -> payload = StateInfo.loadFrom(byteArray)
                45 -> payload = Acknowledgement()
                50 -> payload = StateLocation.loadFrom(byteArray)
                53 -> payload = StateGroup.loadFrom(byteArray)
                59 -> payload = EchoResponse.loadFrom(byteArray)
                107 -> payload = StateLight.loadFrom(byteArray)
                118 -> payload = StatePowerLight.loadFrom(byteArray)
                121 -> payload = StateInfrared.loadFrom(byteArray)
                else -> throw RuntimeException("Code not recgonised")
            }

            return payload
        }
    }
}