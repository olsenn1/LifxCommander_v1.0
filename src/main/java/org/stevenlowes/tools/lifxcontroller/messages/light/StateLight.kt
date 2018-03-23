package org.stevenlowes.tools.lifxcontroller.messages.light

import org.stevenlowes.tools.lifxcontroller.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.HSBK
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.payloads.CustomWritePayload
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Levels

import java.math.BigInteger

class StateLight(var color: HSBK = HSBK(),
                 var reserved1: Int = 0,
                 var power: Int = Levels.MIN,
                 var label: String = "",
                 var reserved2: BigInteger = BigInteger.ZERO) : CustomWritePayload(107) {

    override fun setFromCommandByteArray(byteArray: ByteArray) {
        var hueBinStr = ""
        for (i in 37 downTo 36) {
            hueBinStr += CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        val hueInt = Integer.parseInt(hueBinStr, 2)
        color.hue = Hue(hueInt)

        var saturationBinStr = ""
        for (i in 39 downTo 38) {
            saturationBinStr = saturationBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        color.saturation = Integer.parseInt(saturationBinStr, 2)
        var brightnessBinStr = ""
        for (i in 41 downTo 40) {
            brightnessBinStr = brightnessBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        color.brightness = Integer.parseInt(brightnessBinStr, 2)
        var kelvinBinStr = ""
        for (i in 43 downTo 42) {
            kelvinBinStr = kelvinBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        color.kelvin = Integer.parseInt(kelvinBinStr, 2)

        var reserved1BinStr = ""
        for (i in 45 downTo 44) {
            reserved1BinStr = reserved1BinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        reserved1 = Integer.parseInt(reserved1BinStr, 2)

        var powerBinStr = ""
        for (i in 47 downTo 46) {
            powerBinStr = powerBinStr + CommonMethods.convertByteToBinaryString(byteArray[i])
        }
        power = Integer.parseInt(powerBinStr, 2)

        val labelBytes = ByteArray(32)
        System.arraycopy(byteArray, 48, labelBytes, 0, 32)
        label = String(labelBytes)

        val reserved2Bytes = ByteArray(8)
        for (i in 87 downTo 80) {
            reserved2Bytes[-1 * i + 87] = byteArray[i]
        }
        reserved2 = BigInteger(reserved2Bytes)
    }
}