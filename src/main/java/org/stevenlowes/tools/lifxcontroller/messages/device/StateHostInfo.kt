package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload

class StateHostInfo(var signal: Float = 0F,
                    var tx: Long = 0L,
                    var rx: Long = 0L,
                    var reserved: Int = 0) : CustomWritePayload(13) {
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        var signalBinStr = ""
        for (i in 39 downTo 36) {
            signalBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        val signalInt = Integer.parseInt(signalBinStr, 2)
        signal = java.lang.Float.intBitsToFloat(signalInt)

        var txBinStr = ""
        for (i in 43 downTo 40) {
            txBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        tx = java.lang.Long.parseLong(txBinStr, 2)

        var rxBinStr = ""
        for (i in 47 downTo 44) {
            rxBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        rx = java.lang.Long.parseLong(rxBinStr, 2)

        var reservedBinStr = ""
        for (i in 49 downTo 48) {
            reservedBinStr += Utils.convertByteToBinaryString(byteArray[i])
        }
        reserved = Integer.parseInt(reservedBinStr, 2)
    }
}


