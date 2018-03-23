package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponsePayload

data class StateWifiInfo(val signal: Float = 0f,
                    val tx: Long = 0,
                    val rx: Long = 0,
                    val reserved: Int = 0) : ResponsePayload(
        17) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateWifiInfo{
            var signalBinStr = ""
            for (i in 39 downTo 36) {
                signalBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val signalInt = Integer.parseInt(signalBinStr, 2)
            val signal = java.lang.Float.intBitsToFloat(signalInt)

            var txBinStr = ""
            for (i in 43 downTo 40) {
                txBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val tx = java.lang.Long.parseLong(txBinStr, 2)

            var rxBinStr = ""
            for (i in 47 downTo 44) {
                rxBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val rx = java.lang.Long.parseLong(rxBinStr, 2)

            var reservedBinStr = ""
            for (i in 49 downTo 48) {
                reservedBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val reserved = Integer.parseInt(reservedBinStr, 2)

            return StateWifiInfo(signal, tx, rx, reserved)
        }
    }


}
