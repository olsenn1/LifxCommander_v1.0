package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateService(var service: Int = 0, var port: Int = 0) : CustomWritePayload(3) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val serviceBinStr = Utils.convertByteToBinaryString(byteArray[36])
        service = Integer.parseInt(serviceBinStr, 2)

        var portBinStr = ""
        for (i in 40 downTo 37) {
            portBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        port = Integer.parseInt(portBinStr, 2)
    }
}
