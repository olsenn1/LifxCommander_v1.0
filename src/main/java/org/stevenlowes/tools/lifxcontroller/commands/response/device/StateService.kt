package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand

data class StateService(val service: Int = 0, val port: Int = 0) : ResponseCommand(3) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateService{
            val serviceBinStr = Utils.convertByteToBinaryString(byteArray[36])
            val service = Integer.parseInt(serviceBinStr, 2)

            var portBinStr = ""
            for (i in 40 downTo 37) {
                portBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val port = Integer.parseInt(portBinStr, 2)
            return StateService(service, port)
        }
    }
}
