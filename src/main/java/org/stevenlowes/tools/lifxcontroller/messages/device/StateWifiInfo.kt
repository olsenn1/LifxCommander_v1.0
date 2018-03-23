package org.stevenlowes.tools.lifxcontroller.messages.device

import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.SetOnlyPayload

class StateWifiInfo(var signal: Float = 0f, var tx: Long = 0, var rx: Long = 0, var reserved: Int = 0) : SetOnlyPayload(17){
    override fun setFromCommandByteArray(byteArray: ByteArray) {
        var signalBinStr = ""
        for (i in 39 downTo 36) {
            signalBinStr = signalBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        val signalInt = Integer.parseInt(signalBinStr, 2)
        signal = java.lang.Float.intBitsToFloat(signalInt)

        var txBinStr = ""
        for (i in 43 downTo 40) {
            txBinStr = txBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        tx = java.lang.Long.parseLong(txBinStr, 2)

        var rxBinStr = ""
        for (i in 47 downTo 44) {
            rxBinStr = rxBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        rx = java.lang.Long.parseLong(rxBinStr, 2)

        var reservedBinStr = ""
        for (i in 49 downTo 48) {
            reservedBinStr = reservedBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        reserved = Integer.parseInt(reservedBinStr, 2)
    }


}