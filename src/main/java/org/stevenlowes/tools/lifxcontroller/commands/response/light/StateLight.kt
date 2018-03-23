package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Temp
import java.math.BigInteger

data class StateLight(val color: Color = Color(),
                      val reserved1: Int = 0,
                      val power: Level = Level.MIN,
                      val label: String = "",
                      val reserved2: BigInteger = BigInteger.ZERO) : ResponseCommand(107) {

    companion object {
        fun loadFrom(byteArray: ByteArray): StateLight {
            val color = Color()

            var hueBinStr = ""
            for (i in 37 downTo 36) {
                hueBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val hueInt = Integer.parseInt(hueBinStr, 2)
            color.hue = Hue(hueInt)

            var saturationBinStr = ""
            for (i in 39 downTo 38) {
                saturationBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            color.saturation = Level(Integer.parseInt(saturationBinStr, 2))
            var brightnessBinStr = ""
            for (i in 41 downTo 40) {
                brightnessBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            color.brightness = Level(Integer.parseInt(brightnessBinStr, 2))
            var kelvinBinStr = ""
            for (i in 43 downTo 42) {
                kelvinBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }

            color.temp = Temp(Integer.parseInt(kelvinBinStr, 2))

            var reserved1BinStr = ""
            for (i in 45 downTo 44) {
                reserved1BinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val reserved1 = Integer.parseInt(reserved1BinStr, 2)

            var powerBinStr = ""
            for (i in 47 downTo 46) {
                powerBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val power = Level(Integer.parseInt(powerBinStr, 2))

            val labelBytes = ByteArray(32)
            System.arraycopy(byteArray, 48, labelBytes, 0, 32)
            val label = String(labelBytes)

            val reserved2Bytes = ByteArray(8)
            for (i in 87 downTo 80) {
                reserved2Bytes[-1 * i + 87] = byteArray[i]
            }
            val reserved2 = BigInteger(reserved2Bytes)

            return StateLight(color, reserved1, power, label, reserved2)
        }
    }
}