package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestPayload
import org.stevenlowes.tools.lifxcontroller.values.Color

data class SetColor(val reserved: Int = 0, var color: Color = Color(), var duration: Long = 0) : RequestPayload(102) {
    override val byteArray: ByteArray
        get() {
            val byteArray = ByteArray(13)

            val reservedByte: ByteArray = Utils.toByteArray(1, reserved)
            byteArray[0] = reservedByte[0]

            val hueBytes: ByteArray = color.hue.byteArray
            byteArray[1] = hueBytes[0]
            byteArray[2] = hueBytes[1]

            val saturationBytes: ByteArray = color.saturation.byteArray
            byteArray[3] = saturationBytes[0]
            byteArray[4] = saturationBytes[1]

            val brightnessBytes: ByteArray = color.brightness.byteArray
            byteArray[5] = brightnessBytes[0]
            byteArray[6] = brightnessBytes[1]

            val kelvinBytes: ByteArray = color.temp.byteArray
            byteArray[7] = kelvinBytes[0]
            byteArray[8] = kelvinBytes[1]

            val durationBytes: ByteArray = Utils.toByteArray(4, duration)
            byteArray[9] = durationBytes[0]
            byteArray[10] = durationBytes[1]
            byteArray[11] = durationBytes[2]
            byteArray[12] = durationBytes[3]

            return byteArray
        }
}
