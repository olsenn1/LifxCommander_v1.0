package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

class StateService(var service: Int = 0, var port: Int = 0) : SetOnlyPayload(3) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        val serviceBinStr = CommonMethods.convertByteToBinaryString(byteArray[36])
        service = Integer.parseInt(serviceBinStr, 2)

        var portBinStr = ""
        for (i in 40 downTo 37) {
            portBinStr = portBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        port = Integer.parseInt(portBinStr, 2)
    }

}